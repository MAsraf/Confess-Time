#Jun-Ying 's Branch
# Confess-Time 
Confession page simulator application, implement some data structures &amp; algorithms

# Basic Requirements
# - Submit Confession Post
Create an input interface which allows users to submit their confessions.
Class: Confession Post
Variable:
- postID    //Confession post ID    | String
- content   //Confession content    | String
- postTime  //Confession post published date &amp; time  | Date

# - View Published Confession Posts
# - Search Published Confession Posts

# Admin Panels and System
# - Waiting / Queuing list
Process:
1. User submits a confession through the input interface.
2. Program check for content spamming.
3. The confession data is pushed into a data structure.
4. Waiting time elapsed.
5. The confession data is popped from that data structure.
6. The popped confession data is stored in the program permanently.

Rules:
a. If the number of elements in the data structure is less than or equals to 5, pop the data every 15 minutes.
b. If the number of elements in the data structure is less than or equals to 10, pop the data every 10 minutes.
c. If the number of elements in the data structure is more than 10, pop the data every 5 minutes.

# - Spam Checking
Use at least one algorithm with appropiate data structures to block spammed confessions.

# - Batch Removal
Delete all posts that are replying to a post(given a confession ID), and remove itself as well, if the post is too controversial and lead to unnecessary argument in the community.

# - Save and Load Files
Store confession posts data in a permanent storage locally or cloud before exiting the program. Load the data back when application is restarted.

# Additional Challenges / Extra Features
# i. GUI
# ii. Vacation Mode
Automatically approved/disapprove post based on neutral, good tone or bad tone contained in it(rude words / harassmement / blackmail / racism etc.).
Ban bad user.
# iii. Facebook API Integration
# iv. More than Text
Upload media files too.
