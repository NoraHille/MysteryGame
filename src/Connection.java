public class Connection {

    Sight leftSight;
    Sight rightSight;
    ConnectionType connectionType = ConnectionType.BOTH; // the side the arrow between the sights points to

    public Connection(Sight leftSight, Sight rightSight, ConnectionType connectionType) {
        this.leftSight = leftSight;
        this.rightSight = rightSight;
        this.connectionType = connectionType;
    }

    public Connection(Sight leftSight, Sight rightSight) {
        this.leftSight = leftSight;
        this.rightSight = rightSight;
    }

    public boolean hasSight(Sight s){
        if(connectionType == ConnectionType.BOTH){
            return (leftSight == s || rightSight == s);
        }
        if(connectionType == ConnectionType.LEFT){
            return rightSight == s;
        }
        if(connectionType == ConnectionType.RIGHT){
            return leftSight == s;
        }
        else{
            return false;
        }

    }

    public ConnectionType getTypeOfSight(Sight s){
        if(connectionType == ConnectionType.BOTH){
            if(leftSight == s){
                return ConnectionType.RIGHT;
            }
            else{
                return ConnectionType.LEFT;
            }
        }
        else{
            return connectionType;
        }
    }
}


enum ConnectionType{
    LEFT, RIGHT, BOTH
}
