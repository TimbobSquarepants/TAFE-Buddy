package campus_map_classes.Campus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CampusContent {

    /**
     * An array of sample Campuses.
     */
    public static final List<Campus> CAMPUSES = new ArrayList<>();

    /**
     * A map of Campuses, by ID.
     */
    public static final Map<String, Campus> CAMPUS_MAP = new HashMap<String, Campus>();


    static {
        // Add some sample Campuses.
        addCampus(new Campus("1","Adelaide City Campus", "College"));
        addCampus(new Campus("2","Tonsley Campus","Technical School"));
        addCampus(new Campus("3","Urrbrae Campus","Adult Education School"));
        addCampus(new Campus("4","Morphetville Campus","Education"));
        addCampus(new Campus("5","Regency Campus","College"));
        addCampus(new Campus("6","Port Adelaide Campus","Technical School"));
        addCampus(new Campus("7","Tea Tree Gully Campus","Training Centre"));
        addCampus(new Campus("8","Giles Plains Campus","Higher Education"));
        addCampus(new Campus("9","Parafield Campus","Learning Centre"));
        addCampus(new Campus("10","Elizabeth Campus","College"));
        addCampus(new Campus("11","Gawler Campus","College"));
        addCampus(new Campus("12","Noarlunga Campus","Higher Learning School"));
        addCampus(new Campus("12","Mount Barker Campus","Higher Learning School"));

    }

    private static void addCampus(Campus campus) {
        CAMPUSES.add(campus);
        CAMPUS_MAP.put(campus.id,campus);
    }

    /**
     * Setting up the Campus Class
     */
    public static class Campus {
        public final String id;
        public final String campusName;
        public final String campusDetails;

        public Campus(String id, String CampusName, String CampusDetails) {
            this.id = id;
            this.campusName = CampusName;
            this.campusDetails = CampusDetails;
        }

        @Override
        public String toString() {
            return campusName + " \n" + campusDetails;
        }
    }
}
