package posions;

import basemod.abstracts.CustomPotion;
import characters.Apex;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.GameDictionary;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.potions.BlockPotion;
import powers.SchemePower;
import powers.SheathPower;

/**
 * Date:2022/6/22
 * Author:Vent
 * Description:
 **/
public class SheathPotion extends CustomPotion {
    public static final String POTION_ID = "SheathPotion";
    // 能力的本地化字段
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);
    // 能力的名称
    private static final String NAME = potionStrings.NAME;
    // 能力的描述
    private static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;
    public SheathPotion() {
        super(NAME, POTION_ID, PotionRarity.UNCOMMON, PotionSize.S, PotionColor.FIRE);
        this.isThrown = false;
        this.labOutlineColor = Apex.SILVER;
    }

    public void initializeData() {
        this.potency = this.getPotency();
        this.description = potionStrings.DESCRIPTIONS[0] + this.potency + potionStrings.DESCRIPTIONS[1];
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
        this.tips.add(new PowerTip(TipHelper.capitalize(GameDictionary.BLOCK.NAMES[0]), (String)GameDictionary.keywords.get(GameDictionary.BLOCK.NAMES[0])));
    }

    public void use(AbstractCreature target) {
        this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new SheathPower(AbstractDungeon.player, this.potency),this.potency));
    }

    public int getPotency(int ascensionLevel) {
        return 1;
    }

    public AbstractPotion makeCopy() {
        return new SheathPotion();
    }
}
