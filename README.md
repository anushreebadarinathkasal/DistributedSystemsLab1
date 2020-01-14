# DistributedSystemsLab1
Objectives:
  1. Understanding two communication protocols for distributed processing, HyperText Transport Protocol - HTTP, and Sockets.
  2. Exposure to multithreading.
  3. Using an Integrated Development Environment (IDE)
  Project Specification:
These will be individual projects. You may write the program in any language that is supported under any Integrated Development Environment (IDE). Keep in mind that more help may be available to you in some languages than in others. Furthermore, available controls, objects, libraries etc. may make some of these tasks easier in one language than in another. Finally, because of the lack of restrictions on IDEs, you will have to have that IDE available to demo to the TA. For example, you might bring a laptop to demo the program. Socket programming is so universal that you can probably find major portions of this part of the program with searching on Google. Using code you find on the Internet is fine, but be sure to document the source in the writeup and in the program source code!
You will write a system consisting of a server and three client processes. Each client process will connect to the server over a socket connection and register a user name at the server. The server should be able to handle all three clients concurrently and display the names of the connected clients in real time. The server should be a passive component that acts only to facilitate message delivery.
Each client will allow a user to enter a brief text message. The user will enter a text message and indicate one of two delivery options:
1. Deliver the message to a single, designated client (1-to-1); or,
2. Deliver the message to all clients (1-to-N).
If the user selects option 1, the client should prompt the user to designate which client the message should be delivered to. How the user indicates which client should receive the message is left to the developer’s discretion.
If the user selects option 2, the message should be delivered to all clients. Whether the client receives its own message is left to the developer’s discretion.
When a client receives a message from a peer, the message should be printed to the screen and include the name of the originating peer, as well as whether the message was 1-to-1 or 1-to-N.
The server and the client should each be managed with a simple GUI. The GUI should provide a way kill the process without using the ‘exit’ button on the window. Messages exchanged between server and client should use HTTP formats and commands.
The HTTP tags must use, at minimum, Host, User-Agent, Content-Type, Content-Length, and Date. If you are polling the server, use GET. If you are sending data to the server, use POST.
The required actions are summarized as follows:
Client
The client will execute the following sequence of steps:
1. Connect to the server via a socket.
2. Provide the server with a unique user name. The user name may be:
a. A string provided by the user; or,
b. Some value associated with the process.
3. Proceed to send and receive messages until killed by the user.
Sending messages:
1. Accept a brief text message from a user.
2. Prompt the user to indicate the delivery preference. If delivering the message to a single client is indicated, the user should be prompted to specify which client.
3. Encode the message in HTTP and upload to the server.
4. Indicate that the message has been successfully sent to the server.
Receiving messages:
1. Wait for a message to be received from the server.
2. Parse the HTTP metadata and print the message to the GUI.
3. Show which client sent the message.
4. Indicate whether the message was 1-to-1 or 1-to-N.
Server
The server should support three concurrently connected clients and display a list of which clients are connected in real-time. The server will execute the following sequence of steps:
1. Startup and listen for incoming connections.
2. Print that a client has connected and fork a thread to handle that client.
3. Accept and forward messages received from users.
4. Print the unparsed HTTP message received from a client to the screen.
5. Begin at step 3 until connection is closed by the client.
The server must correctly handle an unexpected client disconnection without crashing. When a client disconnects from the server, the server GUI must indicate this to the user in real time. The server should print messages both received from, and sent to, the client in unparsed HTTP so that the grader can verify the format.
Your program must operate independently of a browser. Date/time on the messages should be encoded according to HTTP.
