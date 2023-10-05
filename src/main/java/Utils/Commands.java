package Utils;

import Constants.ApplicationConstants;
import Exceptions.InvalidCommand;

public class Commands {

    private String l_rootCommand;
    private String[] l_parameters;
    private String[] l_multipleParameter;
    private String l_firstParameter;
    private String l_secondParameter;
    private String l_thirdParameter;
    private String l_command;

    public Commands(String p_command ){
        // if (p_command == " " || p_command == null || p_command.trim().isEmpty()) {
        //     new InvalidCommand("Command cannot be null or empty.");
        //     return;
        // }
        // else{
            getRootCommandWord(p_command);
            // validateCommand();
        // }
    }

    public String getL_rootCommand() {
        return l_rootCommand;
    }
    
    public boolean validateCommand(){
        if ( l_parameters == null || l_parameters.length < 1) {
            new InvalidCommand("Command cannot be null or empty.");
            return false;
        }

        l_rootCommand = l_parameters[0];
        // use  l_rootCommand to check the type of command
        switch (l_rootCommand) {

            /*
             * Game Phase 1:
             */
            case ApplicationConstants.EDITMAP:
            case ApplicationConstants.SAVEMAP:
            case ApplicationConstants.LOADMAP: {
                if (l_parameters.length >= 2 && !l_parameters[1].isEmpty()) {
                    l_firstParameter = l_parameters[1];
                    return true;
                } else {
                    new InvalidCommand("Recieved invalid command format for " + l_command);
                    return false;
                }
            }
            case ApplicationConstants.SHOWMAP:{ 
                if (l_parameters.length == 1 && !l_parameters[0].isEmpty()) {
                    return true;
                } else {
                    new InvalidCommand("Invalid command format for  \"" + l_command + "\" try 'showmap' instead.");
                    return false;
                }
            }
            case ApplicationConstants.EDITCONTINENT: {
                if (l_parameters.length < 2) {
                    new InvalidCommand("Invalid command format for EditConotinent, \ntry: 'editcontinent -add continentID continentvalue/ -remove continentID'");
                    return false;
                }
                if (l_parameters.length > 3){
                        this.l_multipleParameter = l_command.split("-");
                        for(String singleCommand:l_multipleParameter){
                            System.out.println(singleCommand);
                            l_parameters = singleCommand.split(" ");
                            System.out.println(l_parameters);
                            switch (l_parameters[0]) {
                                case ApplicationConstants.ADD:{
                                    try {
                                        l_firstParameter = l_parameters[0];
                                        if(l_parameters[1]!=null && !l_parameters[1].isEmpty()) {
                                            l_secondParameter = l_parameters[1];
                                            if(Utils.isInteger(l_parameters[2])) {
                                                l_thirdParameter = l_parameters[2];
                                                return true;
                                            }else {
                                                    new InvalidCommand("Invalid parameter format for " + l_command);
                                                    return false;
                                                }
                                        }else {
                                            new InvalidCommand("Invalid command recieved: " + l_command);
                                            return false;
                                        }
                                    } catch (Exception e) {
                                        new InvalidCommand("Invalid command recieved: " + l_command);
                                        return false;
                                    }
                                    
                                }
                                case ApplicationConstants.REMOVE: {
                                    try {
                                         l_firstParameter = l_parameters[0];
                                        if(l_parameters[1]!=null && !l_parameters[1].isEmpty()) {
                                            l_secondParameter = l_parameters[1];
                                            return true;
                                        }else {
                                            new InvalidCommand("Invalid parameter format for " + l_command);
                                            return false;
                                                }
                                        }
                                    catch (Exception e) {
                                    new InvalidCommand("\nInvalid command format for Add/Remove " + l_command);
                                    return false;
                                    }
                                }
                            }
                        }
                    }else{
                        switch (l_parameters[1]) {
                                case "-"+ApplicationConstants.ADD:{
                                    try {
                                        l_firstParameter = l_parameters[1];
                                        if(l_parameters[2]!=null && !l_parameters[2].isEmpty()) {
                                            l_secondParameter = l_parameters[2];
                                            if(Utils.isInteger(l_parameters[3])) {
                                                l_thirdParameter = l_parameters[3];
                                                return true;
                                            }else {
                                                    new InvalidCommand("Invalid parameter format for " + l_command);
                                                    return false;
                                                }
                                        }else {
                                            new InvalidCommand("Invalid command recieved: " + l_command);
                                            return false;
                                        }
                                    } catch (Exception e) {
                                        new InvalidCommand("Invalid command recieved: " + l_command);
                                        return false;
                                    }
                                    
                                }
                                case "-"+ApplicationConstants.REMOVE: {
                                    try {
                                         l_firstParameter = l_parameters[1];
                                        if(l_parameters[2]!=null && !l_parameters[2].isEmpty()) {
                                            l_secondParameter = l_parameters[2];
                                            return true;
                                        }else {
                                            new InvalidCommand("Invalid parameter format for " + l_command);
                                            return false;
                                                }
                                        }
                                    catch (Exception e) {
                                    new InvalidCommand("\nInvalid command format for Add/Remove " + l_command);
                                    return false;
                                    }
                                }
                            }
                    }
                }
            case ApplicationConstants.EDITCOUNTRY:
            case ApplicationConstants.EDITNEIGHBOR: {
                if (l_parameters.length < 2) {
                    new InvalidCommand("Invalid command format for " + l_command);
                    return false;
                }
                switch (l_parameters[1]) {
                    case "-" + ApplicationConstants.ADD:{
                        try {
                            l_firstParameter = l_parameters[1];
                            if(l_parameters[2]!=null && !l_parameters[2].isEmpty()) {
                                l_secondParameter = l_parameters[2];
                                if(Utils.isInteger(l_parameters[3])) {
                                    l_thirdParameter = l_parameters[3];
                                    return true;
                                }else {
                                        new InvalidCommand("Invalid parameter format for " + l_command);
                                        return false;
                                    }
                            }else {
                                new InvalidCommand("Invalid command recieved: " + l_command);
                                return false;
                            }  
                        } catch (Exception e) {
                                new InvalidCommand("Invalid command recieved: " + l_command);
                                return false;
                        }
                        
                    }
                    case "-" + ApplicationConstants.REMOVE: {
                        try {
                            l_firstParameter = l_parameters[1];
                            if (l_parameters.length >= 3 && !l_parameters[2].isEmpty()) {
                                return true;
                            } else {
                                new InvalidCommand("\nInvalid parameter recieved: " + l_command);
                                return false;
                            }
                        } catch (Exception e) {
                                new InvalidCommand("\nInvalid parameter recieved: " + l_command);
                                return false;
                        }
                    }
                    default:
                        new InvalidCommand("\nInvalid command format for Add/Remove " + l_command + " try '-add/-remove <parameter>' ");
                        return false;
                }
            }
            case ApplicationConstants.VALIDATEMAP:
            case ApplicationConstants.EXIT:{
                return true; 
            }

            /*
             * Game Phase 2:
             */
            case ApplicationConstants.ASSIGNCOUNTRIES:{
                return true;
            }
            case ApplicationConstants.GAMEPLAYER: {
                if (l_parameters.length < 2) {
                    new InvalidCommand("Invalid command format for GamePlayer, \ntry: 'gameplayer -add/-remove playername'");
                    return false;
                }
                switch (l_parameters[1]) {
                    case "-" + ApplicationConstants.ADD:
                    case "-" + ApplicationConstants.REMOVE: {
                        try {
                            l_firstParameter = l_parameters[1];
                            if (l_parameters.length >= 3 && !l_parameters[2].isEmpty()) {
                                l_secondParameter = l_parameters[2];
                                return true;
                            } else {
                                new InvalidCommand("Invalid command parameter for Add/Remove.");
                                return false;
                            }
                        } catch (Exception e) {
                            new InvalidCommand("Invalid command parameter for Add/Remove.");
                            return false;
                        }
                    }
                    default:
                        new InvalidCommand("Invalid command format for GamePlayer, \ntry: 'gameplayer -add/-remove playername'");
                        return false;
                }
            }
            case ApplicationConstants.DEPLOY: {
                if (l_parameters.length < 3) {
                    new InvalidCommand("Invalid command format for  " + l_command + "\ntry: deploy countryID num");
                    return false;
                }
                try {
                    Integer.parseInt(l_parameters[1]);
                    Integer.parseInt(l_parameters[2]);
                    return true;
                } catch (NumberFormatException ex) {
                    new InvalidCommand("Invalid NumberFormate for " + l_command);
                    return false;
                }
            }

            default:
                new InvalidCommand("Invalid command recieved: " + l_command);
                return false;
        }
    }

    /**
     * split the command by space
     * @param p_command the command entered by user
     */
    public void getRootCommandWord(String p_command) {
        try {
            this.l_parameters = p_command.split(" ");
            this.l_command = p_command;
        } catch (Exception e) {
            new InvalidCommand(p_command);
        }
    }

    /**
     * get the command array split by space
     * @return the command array split by space
     */
    public String[] getL_parameters() {
        return l_parameters;
    }

    /**
     * get the second word of command
     * @return the second word of command
     */
    public String getL_firstParameter() {
        return l_firstParameter;
    }

    /**
     * get the third word of command
     * @return the third word of command
     */
    public String getL_secondParameter() {
        return l_secondParameter;
    }

    /**
     * get the forth word of command
     * @return the forth word of command
     */
    public String getL_thirdParameter() {
        return l_thirdParameter;
    }

}
