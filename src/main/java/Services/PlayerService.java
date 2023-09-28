package Services;

public class PlayerService implements IPlayerService{
    IMapService mapService;
    public PlayerService(IMapService _mapService) {
        mapService = _mapService;
    }
}
