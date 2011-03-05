package models.tabularasa;

import play.db.jpa.Model;

import javax.persistence.Entity;

/**
 * @author Steve Chaloner (steve@objectify.be).
 */
@Entity
public class TableOwner extends Model
{
    public String ownerKey;

    private TableOwner(Builder builder)
    {
        ownerKey = builder.ownerKey;
    }

    public static final class Builder
    {
        private String ownerKey;

        public Builder()
        {
        }

        public Builder ownerKey(String ownerKey)
        {
            this.ownerKey = ownerKey;
            return this;
        }

        public TableOwner build()
        {
            return new TableOwner(this);
        }
    }
}
