import React from "react";
import ReactDOM from "react-dom/client";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import { ApolloClient, ApolloLink, ApolloProvider, HttpLink, InMemoryCache } from "@apollo/client";
import { setContext } from "@apollo/client/link/context";
import { ReactKeycloakProvider } from "@react-keycloak/web";
import Keycloak from "keycloak-js";
import App from "./App";

const keycloak = new Keycloak({
  url: import.meta.env.VITE_KEYCLOAK_URL,
  realm: import.meta.env.VITE_KEYCLOAK_REALM,
  clientId: import.meta.env.VITE_KEYCLOAK_CLIENT_ID
});

keycloak.onTokenExpired = () => {
  keycloak
    .updateToken(30)
    .catch(() => keycloak.logout({ redirectUri: window.location.href }));
};

const httpLink = new HttpLink({
  uri: "/admin/graphql"
});

const authLink = setContext(async (_, { headers }) => {
  if (keycloak.authenticated) {
    try {
      await keycloak.updateToken(30);
    } catch (error) {
      keycloak.logout({ redirectUri: window.location.href });
    }
  }
  return {
    headers: {
      ...headers,
      authorization: keycloak.token ? `Bearer ${keycloak.token}` : ""
    }
  };
});

const client = new ApolloClient({
  link: ApolloLink.from([authLink, httpLink]),
  cache: new InMemoryCache()
});

ReactDOM.createRoot(document.getElementById("root") as HTMLElement).render(
  <React.StrictMode>
    <ReactKeycloakProvider authClient={keycloak} initOptions={{ onLoad: "login-required" }}>
      <ApolloProvider client={client}>
        <BrowserRouter basename="/admin">
          <Routes>
            <Route path="/*" element={<App />} />
          </Routes>
        </BrowserRouter>
      </ApolloProvider>
    </ReactKeycloakProvider>
  </React.StrictMode>
);
