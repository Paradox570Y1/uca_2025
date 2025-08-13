import java.util.*;
import java.lang.*;
import java.io.*;


class Message {
    public String msg;
    public int timeStamp;
    Message(String msg,int timeStamp){
      this.msg = msg;
      this.timeStamp = timeStamp;
    }
}

class RobotTracker{    
    HashMap<String,Integer> recentMessages;
    RobotTracker(){
      recentMessages = new HashMap<>();
    }
    private void shouldPrintMessage(Message message) {
        // Add your implementation here
	if(recentMessages.containsKey(message.msg)){
	  int oldTimeStamp = recentMessages.get(message.msg);
	  if(message.timeStamp - oldTimeStamp >= 10){
            recentMessages.put(message.msg,message.timeStamp);
	    System.out.println(message.timeStamp+" "+message.msg);
	  }
	} else {
	  System.out.println(message.timeStamp+" "+message.msg);
          recentMessages.put(message.msg,message.timeStamp);
	}
    }
    public static void main(String[] args){
	RobotTracker robotTracker = new RobotTracker();
	robotTracker.shouldPrintMessage(new Message("hello",1));
	robotTracker.shouldPrintMessage(new Message("hello",2));
	robotTracker.shouldPrintMessage(new Message("bye",8));
	robotTracker.shouldPrintMessage(new Message("hello",12));
	robotTracker.shouldPrintMessage(new Message("bye",13));
    }
}


