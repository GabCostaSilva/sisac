package sisac.helpers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DataFormatada {

    private LocalDate data;
    private DateTimeFormatter formatter;

    public DataFormatada(String data) {
        this.formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.data = LocalDate.parse(data, formatter);
    }

    public DataFormatada(LocalDate data) {
        this.data = data;
    }

    public LocalDate getData() {
        return data;
    }

    public String getDataToString() {
       return data.format(formatter);
    }

}
