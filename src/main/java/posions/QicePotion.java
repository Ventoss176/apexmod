package posions;

import actions.CostReduction;
import actions.QiceAction;
import basemod.abstracts.CustomPotion;
import characters.Apex;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.GameDictionary;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;

/**
 * Date:2022/6/22
 * Author:Vent
 * Description:
 **/
public class QicePotion extends CustomPotion {
    public static final String POTION_ID = "QicePotion";
    // 能力的本地化字段
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);
    // 能力的名称
    private static final String NAME = potionStrings.NAME;
    // 能力的描述
    private static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;
    public QicePotion() {
        super(NAME, POTION_ID, PotionRarity.RARE, PotionSize.SNECKO, PotionColor.BLUE);
        this.isThrown = false;
        this.labOutlineColor = Apex.DEEPRED;
    }

    public void initializeData() {
        this.potency = this.getPotency();
        if (Settings.language == Settings.GameLanguage.ZHS) {
            this.description = potionStrings.DESCRIPTIONS[0] + this.potency + potionStrings.DESCRIPTIONS[1];
        } else{
            this.description = potionStrings.DESCRIPTIONS[0] + this.potency + potionStrings.DESCRIPTIONS[1];
        }
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
        this.tips.add(new PowerTip(TipHelper.capitalize(GameDictionary.BLOCK.NAMES[0]), (String)GameDictionary.keywords.get(GameDictionary.BLOCK.NAMES[0])));
    }

    public void use(AbstractCreature target) {
        for (int i = 0; i < this.potency; i++) {
            this.addToBot(new QiceAction());
        }


    }

    public int getPotency(int ascensionLevel) {
        return 1;
    }

    public AbstractPotion makeCopy() {
        return new QicePotion();
    }
}
