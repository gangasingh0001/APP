package main.java.org.example.Map;

public class Player {
    private String d_PlayerName;
    private int d_PlayerID;
    void SetPlayerID(int p_PlayerID)
    {
        this.d_PlayerID=p_PlayerID;
    }
     int GetPlayerID()
     {
         return  this.d_PlayerID;
     }
     void  SetPlayerName(String p_PlayerName)
     {
         this.d_PlayerName=p_PlayerName;
     }
     String GetPlayerName(String p_PlayerName)
     {
         return this.d_PlayerName;
     }

}
