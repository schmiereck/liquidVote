# Data Model
This section describes the data model used in the application.

## User
Represents a user of the system.

- `id`: Unique identifier for the user.
- `username`: The user's username.
- `email`: The user's email address.
- `password`: The user's hashed password.
- `roles`: The roles assigned to the user (e.g., VERIFIED_USER, REGISTERED, IDENTIFIED).

## Topic
Represents a subject for discussion and decision-making.
- `id`: Unique identifier for the topic.
- `title`: The title of the topic.
- `description`: A detailed description of the topic.
- `parentTopic`: Reference to the parent topic (if any).
- `phase`: The current phase of the topic (e.g., ADMISSION, DISCUSSION, PROPOSAL, VOTING, RESULTS).
- `proposals`: List of proposals associated with the topic.

## Proposal
Represents a proposal made under a topic.
- `id`: Unique identifier for the proposal.
- `title`: The title of the proposal.
- `description`: A detailed description of the proposal.
- `topic`: Reference to the topic this proposal belongs to.
- `createdBy`: Reference to the user who created the proposal.
- `createdAt`: Timestamp of when the proposal was created.

## Circle
Represents a group of users with common interests or goals.
- `id`: Unique identifier for the circle.
- `name`: The name of the circle.
- `description`: A detailed description of the circle.
- `members`: List of users who are members of the circle.
- `administrators`: List of users who are administrators of the circle.

## Vote
Represents a vote cast by a user.
- `id`: Unique identifier for the vote.
- `voter`: Reference to the user who cast the vote.
- `proposal`: Reference to the proposal being voted on.
- `voteType`: The type of vote (e.g., ABSTENTION, DIRECT_YES, DIRECT_NO, DELEGATED_USER, DELEGATED_CIRCLE).
- `delegatedTo`: Reference to the user or circle to whom the vote is delegated (if applicable).
- `timestamp`: Timestamp of when the vote was cast.

## Relationships
- A `User` can create multiple `Proposals`.
- A `User` can belong to multiple `Circles`.
- A `Topic` can have multiple `Proposals`.
- A `Proposals` belongs to one `Topic`.
- A `Proposals` can mapped to the `Proposals` of its parent `Topic`.
- A `Circle` can have multiple `Users` as members and administrators.
- A `Circle` can have one parent `Circle`.
- A `User` can cast multiple `Votes`, each associated with a specific `Proposal`.
- A `Vote` can be a direct vote or a delegated vote to another `User` or `Circle` for a specific `Proposal`.
