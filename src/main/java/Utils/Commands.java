package Utils;

import Constants.ApplicationConstants;

import static Utils.Utils.isInteger;

public class Commands {

    String l_rootCommand;
    private String[] l_parameters;

    private String l_firstParameter;
    private String l_secondParameter;
    private String l_thirdParameter;


    public Commands (String p_command) {
        getRootCommandWord(p_command);
        if(l_parameters.length>=3) l_secondParameter = l_parameters[2];
        if(l_parameters.length>=2) l_firstParameter = l_parameters[1];
        if(l_parameters.length>=4) l_thirdParameter = l_parameters[3];
        if(l_parameters.length>=1) l_rootCommand = l_parameters[0];
    }

    public String getL_rootCommand() {
        return l_rootCommand;
    }

    public boolean validateCommand() {
        l_rootCommand = l_parameters[0];
        switch (l_rootCommand) {
            case ApplicationConstants.EDITMAP, ApplicationConstants.LOADMAP: {
                if(!l_parameters[1].isEmpty()) {
                    l_firstParameter = l_parameters[1];
                    return true;
                }
            }
            case ApplicationConstants.EXIT: {
                return true;
            }
            case ApplicationConstants.SHOWMAP, ApplicationConstants.VALIDATEMAP, ApplicationConstants.ASSIGNCOUNTRIES: {
                return true;
            }
            case ApplicationConstants.GAMEPLAYER: {
                switch (l_parameters[1]) {
                    case "-"+ApplicationConstants.ADD, "-"+ApplicationConstants.REMOVE: {
                        l_firstParameter = l_parameters[1];
                        if(l_parameters[2]!=null && !l_parameters[2].isEmpty()) {
                            l_secondParameter = l_parameters[2];
                            return true;
                        }
                        break;
                    }
                }
            }
            case ApplicationConstants.EDITCONTINENT: {
                switch (l_parameters[1]) {
                    case "-"+ApplicationConstants.ADD, "-"+ApplicationConstants.REMOVE: {
                        l_firstParameter = l_parameters[1];
                        if(l_parameters[2]!=null && !l_parameters[2].isEmpty()) {
                            if(!isInteger(l_parameters[2])){
                                l_secondParameter = l_parameters[2];
                                if(isInteger(l_parameters[3])) {
                                    l_thirdParameter = l_parameters[3];
                                    return true;
                                }
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
            case ApplicationConstants.DEPLOY: {
                try {
                    Integer.parseInt(l_parameters[1]);
                    Integer.parseInt(l_parameters[2]);
                    return true;
                } catch (NumberFormatException ex) {
                    return false;
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

    public String getL_firstParameter() {
        return l_firstParameter;
    }

    public String getL_secondParameter() {
        return l_secondParameter;
    }

    public String getL_thirdParameter() {
        return l_thirdParameter;
    }
}
