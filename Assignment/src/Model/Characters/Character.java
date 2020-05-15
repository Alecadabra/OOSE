package Model.Characters;

import static java.lang.Math.max;
import static java.lang.Math.min;

import Model.Items.Item;

/**
 * Represents a game character.
 */
public abstract class Character
{
    protected String name;
    protected int maxHp;
    protected int hp;
    protected int gold;

    /**
     * Constructor.
     * @param name Name of character
     * @param maxHp Maximum possible health
     * @param gold Gold in posession
     */
    public Character(String name, int maxHp, int gold)
    {
        this.name = name;
        this.maxHp = maxHp;
        this.hp = maxHp;
        this.gold = gold;
    }

    /**
     * Get the damage dealt by this character for an attack.
     * @return Damage integer
     */
    protected abstract int getDamage();

    /**
     * Get the defence of this character for defending agaisnt an attack.
     * @return Defence integer
     */
    protected abstract int getDefence();

    /**
     * Have the character attack another character. The other character will
     * defend against the attack and may or may not take damage.
     * @param character Character to attack
     */
    public void attack(Character character)
    {
        character.defend(getDamage());
        // TODO notify view
    }

    /**
     * Have the character defend against an attack of given damage. If the
     * defence of the character is greater than the damage, there is no effect.
     * If the damage is greater than the defence of the character, the character
     * takes (damage - defence) damage to their health points.
     * @param damage Damage to be taken
     */
    public void defend(int damage)
    {
        hp -= max(0, damage - getDefence());
        // TODO notify view
    }

    /**
     * Heal the character by a given amount of health points. Does not heal
     * beyond the maximum health of the character.
     * @param amount Amount to heal by
     */
    public void heal(int amount)
    {
        hp = min(maxHp, hp + amount);
        // TODO notify view
    }

    /**
     * Use a given item (Eg. a potion) on the character, which will either heal
     * or damage them.
     * @param item Item to use
     */
    public void use(Item item)
    {
        heal(item.getHealing());
        defend(item.getDamage());
        // TODO notify view
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
}
