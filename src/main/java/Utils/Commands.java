package Utils;

import Constants.ApplicationConstants;

import static Utils.Utils.isInteger;

public class Commands {

    String l_rootCommand;
    private String[] l_parameters;

    public Commands (String p_command) {
        getRootCommandWord(p_command);
    }

    public String getL_rootCommand() {
        return l_rootCommand;
    }

    public boolean validateCommand() {
        l_rootCommand = l_parameters[0];
        switch (l_rootCommand) {
            case ApplicationConstants.EDITMAP, ApplicationConstants.LOADMAP: {
                if(!l_parameters[1].isEmpty()) return true;
            }
            case ApplicationConstants.SHOWMAP, ApplicationConstants.VALIDATEMAP: {
                return true;
            }
            case ApplicationConstants.GAMEPLAYER: {
                switch (l_parameters[1]) {
                    case "-"+ApplicationConstants.ADD, "-"+ApplicationConstants.REMOVE: {
                        if(l_parameters[2]!=null && !l_parameters[2].isEmpty()) {
                            return true;
                        }
                        break;
                    }
                }
            }
            case ApplicationConstants.EDITCONTINENT: {
                switch (l_parameters[1]) {
                    case "-"+ApplicationConstants.ADD, "-"+ApplicationConstants.REMOVE: {
                        if(l_parameters[2]!=null && !l_parameters[2].isEmpty()) {
                            if(!isInteger(l_parameters[2])){
                                return isInteger(l_parameters[3]);
                            }
                        }
                        break;
                    }
                }
            }
            case ApplicationConstants.EDITCOUNTRY: {
                switch (l_parameters[1]) {
                    case "-"+ApplicationConstants.ADD, "-"+ApplicationConstants.REMOVE: {
                        return l_parameters[2] != null && !l_parameters[2].isEmpty();
                    }
                }
            }
            case ApplicationConstants.EDITNEIGHBOR: {
                switch (l_parameters[1]) {
                    case "-"+ApplicationConstants.ADD, "-"+ApplicationConstants.REMOVE: {
                        return l_parameters[2] != null && !l_parameters[2].isEmpty();
                    }
                }
            }
        }
        return false;
    }

    public void getRootCommandWord(String p_command) {
        this.l_parameters = p_command.split(" ");
    }

    public String[] getL_parameters() {
        return l_parameters;
    }
}
