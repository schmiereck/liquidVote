# liquidVote

liquidVote provides a platform for voting and decision-making using the Liquid Democracy model. 
It allows users to delegate their voting power to trusted representatives while retaining the ability to vote directly on issues.

## Features
- User registration and authentication
- Proposal creation and management
- Voting and delegation of votes
- Real-time results and analytics
- Secure and transparent voting process
- Responsive design for mobile and desktop

## Architecture
liquidVote is built using a microservices architecture, leveraging technologies such as Spring Boot, GraphQL, and PostgreSQL for the backend, and React for the frontend. The system is designed to be scalable and maintainable, with a focus on security and user experience.

### Authentication and Authorization
* Verified Users (Low)
  * Email Verification.
* Registered Users (Middle)
  * Profile Management.
* Identified Users (High)
  * Identity Verification.

### Toppics / Issues
A Topic describes a subject for discussion and decision-making. 
Each Topic can have multiple Proposals associated with it.
* Hierarchy of Topics
* Mapping Proposals to the Proposals of the parent Topic

### Circles
A Circle is a group of users who share common interests or goals.
* Circle Membership
* Circle Administration
* Circle-based Proposals

### Votes
Transitive delegation of votes is supported, allowing users to delegate their voting power to trusted Representatives or Circles.
* Abstention
  * Can not decide
  * No Proposal is acceptable
* Direct Vote
  * Yes
  * No
* Delegated Vote
    * Delegate to a trusted representative User.
    * Delegate to a trusted Circle.
