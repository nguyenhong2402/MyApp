package vn.su.jobhunt.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException{
    private String messageContent;
    private String messageKey;
    private String[] params;

    public ResourceNotFoundException(String messageContent) {
        this.messageContent = messageContent;
    }

    public ResourceNotFoundException(String msgKey, String[] params) {
        this.messageKey = msgKey;
        this.params = params;
    }
}
