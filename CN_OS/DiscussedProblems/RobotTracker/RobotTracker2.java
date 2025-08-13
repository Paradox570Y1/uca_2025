import java.util.*;
import java.lang.*;
import java.io.*;


class Message {
    public String msg;
    public int timeStamp;
    boolean shouldPrintMessage;
    public Message(String msg, int timeStamp) {
        this.msg = msg;
        this.timeStamp = timeStamp;
        this.shouldPrintMessage = true;
    }
    
    public String toString() {
        return msg + " " + timeStamp + " " + shouldPrintMessage;
    }
}

interface MessageTracker {
    Message getMessage();    
}

class RobotTracker2
{
    MessageTracker messageTracker;
    Map<String, Message> messageTimeStamp;
    Queue<Message> queue;

    public RobotTracker2(MessageTracker messageTracker) {
        this.messageTracker = messageTracker;
        this.messageTimeStamp = new HashMap<>();
        this.queue = new LinkedList<>();
    }
    
    public void pollMessage() {
        while (true) {
            Message message = messageTracker.getMessage();
            shouldPrintMessage(message);
        }
    }
    /*
    
        [1, "Hello"] , [3, "Hello"], [8, "Bye"], [10, "Yoo"] , [12, "Hello"], [13, "Bye"] , ["24", Bye] .........
        Answer is :  [10, "Yoo"] 
    */
    
    public void shouldPrintMessage(Message message) {
        if (messageTimeStamp.containsKey(message.msg)) {
            Message olderMessage = messageTimeStamp.get(message.msg);
            if (message.timeStamp - olderMessage.timeStamp  < 10) {
                olderMessage.shouldPrintMessage = false;
                message.shouldPrintMessage = false;
            }
        }
        
        queue.offer(message);
        messageTimeStamp.put(message.msg, message);
        printTillThisTimeStamp(message.timeStamp - 10);
    }
    
    private void printTillThisTimeStamp(int thresholdTime) {
        while (!queue.isEmpty() && queue.peek().timeStamp <= thresholdTime) {
            Message message = queue.poll();
            if (message.shouldPrintMessage) {
                System.out.println(message.msg + " " + message.timeStamp);
            }
        }
        //System.out.println(thresholdTime + " "  + queue);
        //deleteEntriesWhihchAreOlderThreshold();
    }
    
    
    public static void main(String args[]) {
        RobotTracker2 robotTracker = new RobotTracker2(null);
        robotTracker.shouldPrintMessage(new Message("hello" , 1)); 
        robotTracker.shouldPrintMessage(new Message("hello" , 3));
        robotTracker.shouldPrintMessage(new Message("bye" , 8)); 
        robotTracker.shouldPrintMessage(new Message("yoo" , 10)); //print this
        robotTracker.shouldPrintMessage(new Message("hello" , 12)); 
        robotTracker.shouldPrintMessage(new Message("bye" , 13));
        robotTracker.shouldPrintMessage(new Message("bye" , 24));
        
    }
}
