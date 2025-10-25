// 代码生成时间: 2025-10-25 09:43:21
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ViewScoped
public class MessageNotificationSystem implements Serializable {
    
    // List to store messages
    private List<String> messages = new ArrayList<>();
    
    // Add a new message to the notification system
    public void addMessage(String message) {
        if (message == null || message.trim().isEmpty()) {
            throw new IllegalArgumentException("Message cannot be null or empty.");
        }
        messages.add(message);
    }
    
    // Get the list of messages
    public List<String> getMessages() {
        return messages;
    }
    
    // Clear all messages from the notification system
    public void clearMessages() {
        messages.clear();
    }
    
    // JSF action method to handle message submission
    public String submitMessage() {
        String newMessage = getNewMessage();
        if (newMessage != null && !newMessage.trim().isEmpty()) {
            addMessage(newMessage);
            clearNewMessage();
            return "success"; // Return a navigation outcome
        } else {
            return "error"; // Return a navigation outcome for error handling
        }
    }
    
    // Getter and setter for newMessage
    private String newMessage;
    
    public String getNewMessage() {
        return newMessage;
    }
    
    public void setNewMessage(String newMessage) {
        this.newMessage = newMessage;
    }
    
    // Clear the new message after submission
    private void clearNewMessage() {
        setNewMessage("");
    }
}
