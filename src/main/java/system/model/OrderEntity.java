package system.model;

import javax.persistence.*;

@Entity
@Table(name = "order", schema = "shop")
public class OrderEntity {
    private int id;
    private String name;
    private int price;
    private String kind;
    private String availability;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "price", nullable = false)
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Basic
    @Column(name = "kind", nullable = false, length = 100)
    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    @Basic
    @Column(name = "availability", nullable = false, length = 100)
    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderEntity that = (OrderEntity) o;

        if (id != that.id) return false;
        if (price != that.price) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (kind != null ? !kind.equals(that.kind) : that.kind != null) return false;
        if (availability != null ? !availability.equals(that.availability) : that.availability != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + price;
        result = 31 * result + (kind != null ? kind.hashCode() : 0);
        result = 31 * result + (availability != null ? availability.hashCode() : 0);
        return result;
    }
}
