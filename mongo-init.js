var conn = new Mongo();
var db = conn.getDB("car_crashes");

db.createCollection("crashes");