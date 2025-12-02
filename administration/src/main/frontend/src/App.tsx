import { Navigate, Route, Routes } from "react-router-dom";
import UserList from "./components/UserList";

function App() {
  return (
    <Routes>
      <Route path="users" element={<UserList />} />
      <Route path="*" element={<Navigate to="/users" replace />} />
    </Routes>
  );
}

export default App;

