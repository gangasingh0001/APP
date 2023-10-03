package Services;

import Utils.Commands;

import java.io.FileNotFoundException;

public interface IMapService {
    public void loadData(Commands commands) throws FileNotFoundException;
    public boolean validateGraph();
}
