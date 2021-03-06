package Model.Characters;

import static java.lang.Math.max;
import static java.lang.Math.min;

import Model.Items.Item;
import View.UserInterface;

/**
 * Represents a game character.
 */
public abstract class GameCharacter
{
    protected String name;
    protected int maxHp;
    protected int hp;
    protected int gold;
    // Characters are given UI's to use during a battle
    protected UserInterface ui;

    /**
     * Constructor.
     * @param name Name of character
     * @param maxHp Maximum possible health
     * @param gold Gold in posession
     */
    public GameCharacter(String name, int maxHp, int gold)
    {
        this.name = name;
        this.maxHp = maxHp;
        this.hp = maxHp;
        this.gold = gold;
        this.ui = null;
    }

    /**
     * Get the damage dealt by this character for an attack.
     * @return Damage integer
     * @throws CharacterException If an error occured
     */
    protected abstract int getDamage() throws CharacterException;

    /**
     * Get the defence of this character for defending agaisnt an attack.
     * @return Defence integer
     */
    protected abstract int getDefence() throws CharacterException;

    /**
     * Have the character attack another character. The other character will
     * defend against the attack and may or may not take damage.
     * @param character Character to attack
     * @throws CharacterException If an error occured
     */
    public void attack(GameCharacter character) throws CharacterException
    {
        logAction(String.format("%s attacked %s", this.name, character.name));
        character.defend(getDamage());
    }

    /**
     * Have the character defend against an attack of given damage. If the
     * defence of the character is greater than the damage, there is no effect.
     * If the damage is greater than the defence of the character, the character
     * takes (damage - defence) damage to their health points.
     * @param damage Damage to be taken
     * @throws CharacterException If an error occured
     */
    public void defend(int damage) throws CharacterException
    {
        int dmgTaken = max(0, damage - getDefence());

        hp = max(0, hp - dmgTaken);
        logAction(String.format("%s took %d damage", this.name, dmgTaken));
        
        if(hp < 1)
        {
            logAction(String.format("%s has died!", name));
        }
        else
        {
            logAction(String.format("%s has %d health remaining",
                this.name, this.hp));
        }
    }

    /**
     * Heal the character by a given amount of health points. Does not heal
     * beyond the maximum health of the character.
     * @param amount Amount to heal by
     */
    public void heal(int amount)
    {
        int newHp = min(maxHp, hp + amount);

        logAction(String.format("%s recovered %d health", this.name,
            newHp - hp));
        this.hp = newHp;
    }

    /**
     * Use a given item (Eg. a potion) on the character, which will either heal
     * or damage them (or both).
     * @param item Item to use
     * @throws CharacterException If an error occured
     */
    public void use(Item item) throws CharacterException
    {
        int healing, damage;

        logAction(String.format("%s used on %s", item.getName(), this.name));
        
        healing = item.getHealing();
        if(healing > 0)
        {
            heal(item.getHealing());
        }

        damage = item.getDamage();
        if(damage > 0)
        {
            defend(item.getDamage());
        }
    }

    /**
     * Sets the UserInterface of this character so that it can log noteworthy
     * actions such as attacking.
     * @param ui The UserInterface object to use
     */
    public void setLog(UserInterface ui)
    {
        this.ui = ui;
    }

    /**
     * Logs an action to the user interface, if it has been set.
     * @param message The message to log
     */
    protected void logAction(String message)
    {
        if(this.ui != null)
        {
            ui.log(message);
        }
    }

    /**
     * Get the name of the character.
     * @return Name string
     */
    public String getName()
    {
        return name;
    }

    /**
     * Set the name of the character.
     * @param name Name string
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Get the current health of the character.
     * @return Health integer
     */
    public int getHp()
    {
        return hp;
    }

    /**
     * Get the current amount of gold in posession of this character.
     * @return Gold integer
     */
    public int getGold()
    {
        return gold;
    }

    /**
     * Add an amount of gold to the player
     * @param inGold Amount of gold
     */
    public void addGold(int inGold)
    {
        gold += inGold;
    }

    /**
     * Remove an amount of gold from the player
     * @param inGold Amount of gold
     * @throws CharacterException If the players gold would be below zero after
     * removal, player's gold is unchanged if this is thrown
     */
    public void removeGold(int inGold) throws CharacterException
    {
        if(inGold > gold)
        {
            throw new CharacterException("Insufficient funds");
        }
        else
        {
            gold -= inGold;
        }
    }

    @Override
    public String toString()
    {
        return this.name;
    }
}
