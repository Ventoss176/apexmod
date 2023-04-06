package cards;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.actions.watcher.JudgementAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.GiantTextEffect;
import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;
import apexpathes.AbstractCardEnum;

import java.util.Iterator;

/**
 * Date:2022/6/22
 * Author:Vent
 * Description:虫子杀手
 **/
public class InsectKiller extends CustomCard {
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("InsectKiller");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    private static final int COST = 2;
    // private static final int BLOCK_AMT = 6;
    private static final int UPGRADE_PLUS_BLOCK = 4;
    public static final String ID = "InsectKiller";
    public static final String IMG_PATH = "img/cards_Apex/InsectKiller.png";

    //调用父类的构造方法，传参为super(卡牌ID,卡牌名称，能量花费，卡牌描述，卡牌类型，卡牌颜色，卡牌稀有度，卡牌目标)
    public InsectKiller() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.SKILL, AbstractCardEnum.Apex_COLOR, CardRarity.RARE, CardTarget.NONE);

        // this.tags.add(BaseModCardTags.BASIC_DEFEND);
        // this.baseBlock = BLOCK_AMT;
        this.baseMagicNumber = 30;
        this.magicNumber = 30;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Iterator var4 = AbstractDungeon.getMonsters().monsters.iterator();

        while(var4.hasNext()) {
            AbstractMonster mon = (AbstractMonster)var4.next();
            if (!mon.isDeadOrEscaped()) {
                this.addToBot(new VFXAction(new WeightyImpactEffect(mon.hb.cX, mon.hb.cY, Color.GOLD.cpy())));
                this.addToBot(new WaitAction(0.8F));
                this.addToBot(new VFXAction(new GiantTextEffect(mon.hb.cX, mon.hb.cY)));
                this.addToBot(new JudgementAction(mon, this.magicNumber));
            }
        }

    }
    @Override
    public AbstractCard makeCopy() {
        //复制卡牌时触发
        return (AbstractCard)new InsectKiller();
    }


    @Override
    public void upgrade() {
        //卡牌升级后的效果
        if (!this.upgraded) {
            //更改名字和提高3点格挡
            this.upgradeName();
//            this.upgradeBaseCost(2);
            this.upgradeMagicNumber(10);
            this.initializeDescription();
            // this.upgradeBlock(UPGRADE_PLUS_BLOCK);
        }
    }
}
