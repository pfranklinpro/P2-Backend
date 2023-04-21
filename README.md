
# Sylvester by Team Primary Keys
**[Sylvester Site](http://sylvester-welcome.s3-website-us-east-1.amazonaws.com/)**

## Problem Statement 

The future of Twitter is in doubt. Many people are worried that the potential failure of Twitter will leave people without a public forum. Many new private social media popped up to try to fill the void for established groups. However, that still leaves the need for people to discover new groups, and for new groups to discover potential new members. This project, Sylvester (of Sylvester and Tweety), is designed to serve that need. 

The users login to a bazaar of posts. They can like the posts that they enjoy and they can follow based on the posts they have seen. They can also make posts, which consist of at least a short message or an image, and no more than one of each together. As a stretch goal, they can separate the streams by the users that they are following or liked before. 

## Audience 
Young people (18-35) who are knowledgeable of the web and of Twitter/Facebook or similar social media sites.

# Team
### Scrum Master 

Yuan Cao 

### Frontend Team 
Lead: Elias Gonzalez

Kongyue Chen, Yuan Cao 

### Backend Team
Lead: Ashley Chancellor 

Paul Franklin 


# Technology
## Frontend 
- **Framework** 
    - ReactJS 
    - TailwindCSS 
- **Language** 
    - Typescript 

## Backend 
- **Application Layer** 
   - Java 8 
   - Apache Maven 
   - Spring Boot 
   - JSON Web Tokens  
   - JUnit 
   - Mockito 
   - JDBC  
   - AWS 

- **Persistent Layer** 
    - PostgreSQL on AWS 
    - AWS Server 

## External APIs
- [Giphy API](https://developers.giphy.com/docs/api/)

# Project Design Specifications and Documents
[Entity Relationship Diagram](img/Sylvester-ERD.jpg)

### User Stories
User Stories 
- Can do’s: 

		Create a new account (HTTP POST) 

		Login to their account (HTTP POST) 

		View anyone’s public profiles (HTTP GET) 

		Create post for all users (HTTP POST) 

		View all user posts (HTTP GET) 

		Like a post (HTTP POST) 

		Unlike a post (HTTP DELETE) 

		Reply to a post with a comment (HTTP POST) 

- Cannot do’s: 

		Create a second account with the same username and email 
        
        Create a post for another user who they are not logged in as 
        
        Like their own post  
        
        Optional: Reply to their own post 
        
        Optional: Like a reply to their own post 

### Required Features
- Registration 
- Login 
- User Profile 
- Create a post 
- Post feed 
- Like a post 
- Comments (Replies) 

### Stretch Goals
- Make posts that consists of a picture and words 
- Embed gifs/videos in posts 
- Filter post feed based on follow and like 
- Simple moderation of post by admin 


# Appearance

SCREEENSHOTS GO HERE



