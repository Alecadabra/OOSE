package controller;

/** Checked exception for when a validation check fails in the controller. */
public class ControllerException extends Exception
{
    public ControllerException(String msg)
    {
        super(msg);
    }
    
    public ControllerException(String msg, Throwable cause)
    {
        super(msg, cause);
    }
}
