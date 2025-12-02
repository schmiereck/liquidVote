import { gql, useQuery } from "@apollo/client";

const USERS_QUERY = gql`
  query Users {
    users {
      id
      username
      email
      roles
    }
  }
`;

function UserList() {
  const { data, loading, error } = useQuery(USERS_QUERY);

  if (loading) {
    return <p>Loading...</p>;
  }
  if (error) {
    return <p>Error loading users: {error.message}</p>;
  }

  return (
    <div>
      <h1>User Administration</h1>
      <table>
        <thead>
        <tr>
          <th>ID</th>
          <th>Username</th>
          <th>Email</th>
          <th>Roles</th>
        </tr>
        </thead>
        <tbody>
        {data.users.map((user: any) => (
          <tr key={user.id}>
            <td>{user.id}</td>
            <td>{user.username}</td>
            <td>{user.email}</td>
            <td>{user.roles.join(", ")}</td>
          </tr>
        ))}
        </tbody>
      </table>
    </div>
  );
}

export default UserList;

