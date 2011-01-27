package models;

import models.tabularasa.TableOwner;
import play.db.jpa.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 * @author Steve Chaloner (steve@objectify.be).
 */
@Entity
public class ExampleUser extends Model
{
    public String userName;

    @OneToOne(cascade = CascadeType.ALL)
    public TableOwner tableOwner;

    private ExampleUser(Builder builder)
    {
        userName = builder.userName;
        tableOwner = builder.tableOwner;
    }

    public static ExampleUser findByUserName(String userName)
    {
        return ExampleUser.find("byUserName", userName).first();
    }

    public static final class Builder
    {
        private String userName;
        private TableOwner tableOwner;

        public Builder()
        {
        }

        public Builder userName(String userName)
        {
            this.userName = userName;
            return this;
        }

        public Builder tableOwner(TableOwner tableOwner)
        {
            this.tableOwner = tableOwner;
            return this;
        }

        public ExampleUser build()
        {
            return new ExampleUser(this);
        }
    }
}
