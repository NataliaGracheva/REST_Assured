public class CityWeather {
    int id;
    String name;
    Temp main;

    public CityWeather(int id, String name, Temp main) {
        this.id = id;
        this.name = name;
        this.main = main;
    }

    public Temp getMain() {
        return main;
    }

    public void setMain(Temp main) {
        this.main = main;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
