package one.typex.marketplacejava;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Product {

    @Id
    Long id;
    String title;
    String description;
    String imgUrl;
    Long price;

}
