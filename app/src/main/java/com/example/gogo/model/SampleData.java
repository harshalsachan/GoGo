package com.example.gogo.model;

import java.util.Arrays;
import java.util.List;

public class SampleData {

    public static List<Place> getSamplePlaces() {
        return Arrays.asList(
            new Place(1, "Eiffel Tower",
                "Iconic iron lattice tower on the Champ de Mars in Paris, France. " +
                "Built between 1887 and 1889 as the entrance arch for the 1889 World's Fair.",
                4.8f,
                "https://upload.wikimedia.org/wikipedia/commons/thumb/8/85/" +
                "Eiffel_Tower_from_Champ_de_Mars,_August_2010.jpg/640px-" +
                "Eiffel_Tower_from_Champ_de_Mars,_August_2010.jpg",
                48.8584, 2.2945,
                "Champ de Mars, 5 Av. Anatole France, 75007 Paris, France"),

            new Place(2, "Colosseum",
                "Ancient amphitheater in the center of Rome, Italy. " +
                "Built in 70–80 AD, it is the largest amphitheater ever built.",
                4.7f,
                "https://upload.wikimedia.org/wikipedia/commons/thumb/d/de/" +
                "Colosseo_2020.jpg/640px-Colosseo_2020.jpg",
                41.8902, 12.4922,
                "Piazza del Colosseo, 1, 00184 Roma RM, Italy"),

            new Place(3, "Machu Picchu",
                "Incan citadel set high in the Andes Mountains in Peru, above the Sacred Valley. " +
                "Built in the 15th century, it is one of the Seven Wonders of the World.",
                4.9f,
                "https://upload.wikimedia.org/wikipedia/commons/thumb/e/eb/" +
                "Machu_Picchu%2C_Peru.jpg/640px-Machu_Picchu%2C_Peru.jpg",
                -13.1631, -72.5450,
                "Machu Picchu, Cusco Region, Peru"),

            new Place(4, "Taj Mahal",
                "Ivory-white marble mausoleum on the south bank of the Yamuna river in Agra, India. " +
                "Commissioned in 1631 by the Mughal emperor Shah Jahan.",
                4.9f,
                "https://upload.wikimedia.org/wikipedia/commons/thumb/b/bd/" +
                "Taj_Mahal%2C_Agra%2C_India_edit3.jpg/640px-Taj_Mahal%2C_Agra%2C_India_edit3.jpg",
                27.1751, 78.0421,
                "Dharmapuri, Forest Colony, Tajganj, Agra, 282001, India"),

            new Place(5, "Statue of Liberty",
                "Colossal neoclassical sculpture on Liberty Island in New York Harbor. " +
                "A gift from France to the United States, dedicated on October 28, 1886.",
                4.6f,
                "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a1/" +
                "Statue_of_Liberty_7.jpg/640px-Statue_of_Liberty_7.jpg",
                40.6892, -74.0445,
                "Liberty Island, New York, NY 10004, USA")
        );
    }
}
