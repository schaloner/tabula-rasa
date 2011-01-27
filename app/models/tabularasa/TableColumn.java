package models.tabularasa;

import play.db.jpa.Model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Represents a single column in a table.
 *
 * @author Steve Chaloner (steve@objectify.be).
 */
@Entity
public class TableColumn extends Model
{
    @ManyToOne(optional = false, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST})
    public TableModel tableModel;

    @Column(nullable = false)
    public String columnKey;

    @Column(nullable = false)
    public Integer columnPosition;

    @Column(nullable = false)
    public Boolean visible;

    @Column(nullable = false)
    public Boolean mandatory;

    private TableColumn(Builder builder)
    {
        tableModel = builder.tableModel;
        columnKey = builder.columnKey;
        columnPosition = builder.columnPosition;
        visible = builder.visible;
        mandatory = builder.mandatory;
    }

    public static final class Builder
    {
        private String columnKey;
        private Integer columnPosition;
        private Boolean visible;
        private Boolean mandatory;
        private TableModel tableModel;

        public Builder()
        {
        }

        public Builder tableModel(TableModel tableModel)
        {
            this.tableModel = tableModel;
            return this;
        }

        public Builder columnKey(String columnKey)
        {
            this.columnKey = columnKey;
            return this;
        }

        public Builder columnPosition(Integer columnPosition)
        {
            this.columnPosition = columnPosition;
            return this;
        }

        public Builder visible(boolean visible)
        {
            this.visible = visible;
            return this;
        }

        public Builder mandatory(boolean mandatory)
        {
            this.mandatory = mandatory;
            return this;
        }

        public TableColumn build()
        {
            return new TableColumn(this);
        }
    }
}
