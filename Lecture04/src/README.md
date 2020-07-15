# Lecture 04 - Interprocess Communication

**Java API for UDP datagrams** 

The Java API provides datagram communication by
means of two classes: 

- **_`DatagramPacket`_**: This class provides a constructor that makes an instance out of an array of bytes comprising a message, the length of the message and the Internet address and local port number of the destination socket, as follows:

    `[array of bytes 
containing message | length of message | Internet address | port number]`
    
   An instance of DatagramPacket may be transmitted between processes when one process sends it and another receives it.
   
   The message can be retrieved from the _DatagramPacket_ by means of the method _getData_. The methods _getPort_ and _getAddress_ access the port and Internet address.

- _**`DatagramSocket`**_: This class supports sockets for sending and receiving UDP datagrams. It provides a constructor that takes a port number as its argument, for use by processes that need to use a particular port. It also provides a no-argument constructor that allows the system to choose a free local port. 

    The class _`DatagramSocket`_ provides methods that include the following:
    
    - **send and receive**: These methods are for transmitting datagrams between a pair of sockets. The argument of _send_ is an instance of _DatagramPacket_ containing a message and its destination. The argument of receive is an empty _DatagramPacket_ in which to put the message, its length and its origin. The methods _send_ and _receive_ can throw _IOExceptions_.
    
    - **setSoTimeout**: This method allows a timeout to be set. With a timeout set, the _receive_ method will block for the time specified and then throw an _InterruptedIOException_.
    
    - **connect**: This method is used for connecting to a particular remote port and Internet address, in which case the socket is only able to send messages to and receive messages from that address.
    
    Program for a client that creates a socket, sends a message to a server at port `6789` and then waits to receive a reply.The message is converted to an array of bytes, and the DNS hostname is converted to an Internet address
    
    Program for the corresponding server, which creates a socket bound to its server port (`6789`) and then repeatedly waits to receive a request message from a client, to which it replies by sending back the same message.
    
    
**Java API for TCP streams**

The Java interface to TCP streams is provided in the classes
_`ServerSocket`_ and _`Socket`_:

- **_`ServerSocket`_**: This class is intended for use by a server to create a socket at a server port for listening for _connect_ requests from clients. Its _accept_ method gets a _connect_ request from the queue or, if the queue is empty, blocks until one arrives. The result of executing _accept_ is an instance of _Socket_ – a socket to use for communicating with the client.

- **_`Socket`_**: This class is for use by a pair of processes with a connection.The client uses a constructor to create a socket, specifying the DNS hostname and port of a server. This constructor not only creates a socket associated with a local port but also connects it to the specified remote computer and port number. It can throw an _UnknownHostException_ if the hostname is wrong or an _IOException_ if an IO error occurs.

    Client program in which the arguments of the main method supply a message and the DNS hostname of the server. The client creates a socket bound to the hostname and server port `7896`. It makes a DataInputStream and a DataOutputStream from the socket’s input and output streams, then writes the message to its output stream and waits to read a reply from its input stream. 
    
    Server program opens a server socket on its server port (`7896`) and listens for connect requests. When one arrives, it makes a new thread in which to communicate with the client. The new thread creates a `DataInputStream` and a `DataOutputStream` from its socket’s input and output streams and then waits to read a message and write the same one back. As our message consists of a string, the client and server processes use the method `writeUTF` of `DataOutputStream` to write it to the output stream and the method `readUTF` of `DataInputStream` to read it from the input stream.
    
    