package server;

import com.google.gson.*;
import data.*;
import data.exceptions.*;


import java.lang.reflect.Type;
import java.time.ZonedDateTime;


public class CustomConverter implements JsonSerializer<StudyGroup>, JsonDeserializer<StudyGroup> {
    Gson gson = new Gson();

    @Override
    public JsonElement serialize(StudyGroup studyGroup, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject o = new JsonObject();
        o.addProperty("id", studyGroup.getId());
        o.addProperty("name", studyGroup.getName());
        o.add("coordinates", gson.toJsonTree(studyGroup.getCoordinates()));
        o.addProperty("creationDate", studyGroup.getCreationDate().toString());
        o.add("groupAdmin", gson.toJsonTree(studyGroup.getGroupAdmin()));
        o.addProperty("studentsCount", studyGroup.getStudentsCount());
        o.addProperty("shouldBeExpelled", studyGroup.getShouldBeExpelled());
        o.addProperty("transferredStudents", studyGroup.getTransferredStudents());
        o.addProperty("semesterEnum", studyGroup.getSemesterEnum().toString());
        o.addProperty("eyeColor", studyGroup.getGroupAdmin().getEyeColor().toString());
        o.add("groupAdmin", gson.toJsonTree(studyGroup.getGroupAdmin()));
        o.add("location", gson.toJsonTree(studyGroup.getGroupAdmin().getLocation()));
        return o;
    }

    @Override
    public StudyGroup deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        Integer id = jsonObject.get("id").getAsInt();
        String name = jsonObject.get("name").getAsString();
        long x = jsonObject.get("coordinates").getAsJsonObject().get("x").getAsLong();
        float y = jsonObject.get("coordinates").getAsJsonObject().get("y").getAsFloat();
        Coordinates coordinates = null;
        try {
            coordinates = new Coordinates(x, y);
        } catch (WorldBorderException e) {
            throw new JsonParseException(e);
        }

        ZonedDateTime zonedDateTime = ZonedDateTime.parse(jsonObject.get("creationDate").getAsString());
        long studentsCount = jsonObject.get("studentsCount").getAsLong();
        long shouldBeExpelled = jsonObject.get("shouldBeExpelled").getAsLong();
        int transferredStudents = jsonObject.get("transferredStudents").getAsInt();
        Semester semesterEnum = Semester.valueOf(jsonObject.get("semesterEnum").getAsString());
        Color eyeColor = Color.valueOf(jsonObject.getAsJsonObject("groupAdmin").get("eyeColor").getAsString());
        Person groupAdmin = gson.fromJson(jsonObject.get("groupAdmin").getAsJsonObject(), Person.class);
        groupAdmin.setEyeColor(eyeColor);
        groupAdmin.setLocation(gson.fromJson(jsonObject.get("groupAdmin").getAsJsonObject().get("location").getAsJsonObject(), Location.class));
        return new StudyGroup(id, name, coordinates,
                zonedDateTime, studentsCount, shouldBeExpelled,
                transferredStudents, semesterEnum, groupAdmin);
    }
}
