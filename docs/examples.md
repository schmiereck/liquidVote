# Examples

This section provides practical examples of how to use the liquidVote system for various democratic processes.

## "Stem cell research"
This topic focuses on the ethical and scientific considerations surrounding stem cell research.

* Users
  * **User A**: "Fritz"
  * **User B**: "Franz"
  * **User C**: "Alice"
  * **User D**: "Bob"
* Circles
  * **Circle A**: "Craftsmen"
    - **Member 1**: "**User A** (Fritz)"
    - **Member 2**: "**User B** (Franz)"
    - **Member 3**: "**User D** (Bob)"
  * **Circle B**: "Scientists"
    - **Member 1**: "**User C** (Alice)"
    - **Member 2**: "**User D** (Bob)"
  * **Circle C**: "Green Party"
    - **Member 1**: "**Circle B** (Scientists)"
  * **Circle D**: "Liberal Party"
    - **Member 1**: "**Circle A** (Craftsmen)"
* Topics and Proposals
  * **Topic A**: "Stem cell research"
    - **Proposal 1**: "Allow all forms stem cell research."
    - **Proposal 2**: "Ban all forms of stem cell research."
  * **Topic B**: "Support stem cell research for medical advancements"
    - **Proposal 1**: "Allow stem cell research with strict ethical guidelines."
    - **Proposal 2**: "Allow only embryonic stem cell research."
    - **Proposal 3**: "Allow only adult stem cell research."
    - **Proposal 4**: "Ban all forms of stem cell research."

"**Topic A**" is the parent topic of "**Topic B**".  
"**Topic B > Proposal 1**" and "**Topic B > Proposal 2**" and "**Topic B > Proposal 3**" is mapped to "**Topic A > Proposal 1**".   
"**Topic B > Proposal 4**" is mapped to "**Topic A > Proposal 2**".

If a User or Circle votes on "**Topic A**", their vote will automatically apply to "**Topic B**" based on the mapping.
If a User or Circle votes on "**Topic B**", their vote will overvote "**Topic A**".
