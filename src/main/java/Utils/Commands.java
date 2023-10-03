package Utils;

import Constants.ApplicationConstants;

import static Utils.Utils.isInteger;

/**
 * a class used to store every word in the input command
 */
public class Commands {
    /**
     * the first word of input command
     */
    String l_rootCommand;
    /**
     * used to store the word in command splited by space
     */
    private String[] l_parameters;
    /**
     * the second word of input command
     */
    private String l_firstParameter;
    /**
     * the third word of input command
     */
    private String l_secondParameter;
    /**
     * the forth word of input command
     */
    private String l_thirdParameter;

    /**
     * initial command object
     * @param p_command the input command from consel
     */
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

    /**
     * check the format of command
     * @return if the format of command is correct then return ture else return false
     */
    public boolean validateCommand() {
        l_rootCommand = l_parameters[0];
        // use  l_rootCommand to check the type of command
        switch (l_rootCommand) {
            // for EDITMA and LOADMAP command,l_parameters[1] is the second word of inout command
            //and it is the name of mao, so we just check the user has inout the name of command
            case ApplicationConstants.EDITMAP, ApplicationConstants.LOADMAP: {
                if(!l_parameters[1].isEmpty()) {
                    l_firstParameter = l_parameters[1];
                    return true;
                }
            }
            //for SHOWMAP we do not check anything
            case ApplicationConstants.SHOWMAP, ApplicationConstants.VALIDATEMAP, ApplicationConstants.ASSIGNCOUNTRIES: {
                return true;
            }
            case ApplicationConstants.EXIT: {
                return true;
            }

            // for game player we need check the if the second word is "-.add" or "+.add"
                //then check the if third word stand for the name of player is empty
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

    /**
     * split the command by space
     * @param p_command the command entered by user
     */
    public void getRootCommandWord(String p_command) {
        this.l_parameters = p_command.split(" ");
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
