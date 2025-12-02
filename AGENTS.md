# Agent Development Guidelines

## Code-Style

Use final for all variables which are not re-assigned.
Use `var` for local variables with obvious types.
Use always "this." to access instance variables and functions if they are not static.

In variables, specially the "id" variables, use the complete name of the entity plus "Id" suffix.
Example: userEntityId

For Objects allways use the complete name of the entity as suffix and a specialized prefix for the meaning if needed.
Example: createdUserEntity
Example: var userEntity = ...
