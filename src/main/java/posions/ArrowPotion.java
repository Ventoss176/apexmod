package posions;

import actions.ArrowPotionAction;
import basemod.abstracts.CustomPotion;
import characters.Apex;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import apexpathes.ApexTags;

/**
 * Date:2022/6/22
 * Author:Vent
 * Description:
 **/
public class ArrowPotion extends CustomPotion {
    public static final String POTION_ID = "ArrowPotion";
    // 能力的本地化字段
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);
    // 能力的名称
    private static final String NAME = potionStrings.NAME;
    // 能力的描述
    private static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;
    public ArrowPotion() {
        super(NAME, POTION_ID, PotionRarity.COMMON, PotionSize.FAIRY, PotionColor.GREEN);
        this.isThrown = false;
        this.labOutlineColor = Apex.DEEPRED;
    }

    public void initializeData() {
        this.potency = this.getPotency();
        this.description = potionStrings.DESCRIPTIONS[0] + this.potency + potionStrings.DESCRIPTIONS[1];
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
        // this.description = DESCRIPTIONS[0] + this.potency + DESCRIPTIONS[1];
    }

    public void use(AbstractCreature target) {
        this.addToBot(new ArrowPotionAction(ApexTags.ARROW, this.potency));
    }

    public int getPotency(int ascensionLevel) {
        return 1;
    }

    public AbstractPotion makeCopy() {
        return new ArrowPotion();
    }
}
