package models.tabularasa;

import play.db.jpa.Model;

import javax.persistence.Entity;

/**
 * @author Steve Chaloner (steve@objectify.be).
 */
@Entity
public class TableOwner extends Model
{
    public String key;

    private TableOwner(Builder builder)
    {
        key = builder.key;
    }

    public static final class Builder
    {
        private String key;

        public Builder()
        {
        }

        public Builder key(String key)
        {
            this.key = key;
            return this;
        }

        public TableOwner build()
        {
            return new TableOwner(this);
        }
    }
}
