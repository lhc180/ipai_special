package com.imageco.special.itake.activityImpl.special;

/**
 * Created by IntelliJ IDEA. User: OYQX Date: 11-11-4 Time: 上午11:21
 */
public class GameGlo
{
    

    private int lefttimes=0;//剩余游戏次数
    private Boolean dissmissAble =false;

    private Boolean forResult=false;

    /**
     * Field resIdtemp5
     */
    private int resIdtemp1;

    /**
     * Field resIdtemp2
     */
    private int resIdtemp2;

    /**
     * Field resIdtemp3
     */
    private int resIdtemp3;

    /**
     * Field resIdtemp4
     */
    private int resIdtemp4;

    /**
     * Field resIdtemp5
     */
    private int resIdtemp5;

    /**
     * Field intPos1
     */
    private int intPos1;

    /**
     * Field intPos2
     */
    private int intPos2;

    /**
     * Field intPos3
     */
    private int intPos3;

    /**
     * Field intPos4
     */
    private int intPos4;

    /**
     * Field intPos5
     */
    private int intPos5;

    /**
     * Field strResult
     */
    private String strResult = "";

    /**
     * Field payType
     */
    private String payType = "b";

    /**
     * Field times
     */
    private int times;

    /**
     * Field INSTANCE
     */
    private static GameGlo INSTANCE = null;

    /**
     * Method getInstance returns the instance of this GameGlo object.
     *
     * @return the instance (type GameGlo) of this GameGlo object.
     */
    public static GameGlo getInstance()
    {
        if (INSTANCE == null)
        {
            INSTANCE = new GameGlo();
        }
        return INSTANCE;
    }

    public static void resetInstance(){
          if (INSTANCE != null)
        {
            INSTANCE.setLefttimes(0);
            INSTANCE.setDissmissAble(false);
            INSTANCE.setForResult(false);
            INSTANCE.setIntPos1(0);
            INSTANCE.setIntPos2(0);
            INSTANCE.setIntPos3(0);
            INSTANCE.setIntPos4(0);
            INSTANCE.setIntPos5(0);
            INSTANCE.setPayType("b");
            INSTANCE.setResIdtemp1(0);
            INSTANCE.setResIdtemp2(0);
            INSTANCE.setResIdtemp3(0);
            INSTANCE.setResIdtemp4(0);
            INSTANCE.setResIdtemp5(0);
            INSTANCE.setStrResult("");
            INSTANCE.setTimes(0);

        }

    }
    /**
     * Method getTimes returns the times of this GameGlo object.
     *
     *  Field times
     *
     * @return the times (type int) of this GameGlo object.
     */
    public int getTimes()
    {
        return times;
    }

    /**
     * Method setTimes sets the times of this GameGlo object.
     *
     *  Field times
     *
     * @param times the times of this GameGlo object.
     *
     */
    public void setTimes(int times)
    {
        this.times = times;
    }

    /**
     * Method getResIdtemp2 returns the resIdtemp2 of this GameGlo object.
     *
     *  Field resIdtemp2
     *
     * @return the resIdtemp2 (type int) of this GameGlo object.
     */
    public int getResIdtemp2()
    {
        return resIdtemp2;
    }

    /**
     * Method setResIdtemp2 sets the resIdtemp2 of this GameGlo object.
     *
     *  Field resIdtemp2
     *
     * @param resIdtemp2 the resIdtemp2 of this GameGlo object.
     *
     */
    public void setResIdtemp2(int resIdtemp2)
    {
        this.resIdtemp2 = resIdtemp2;
    }

    /**
     * Method getResIdtemp3 returns the resIdtemp3 of this GameGlo object.
     *
     *  Field resIdtemp3
     *
     * @return the resIdtemp3 (type int) of this GameGlo object.
     */
    public int getResIdtemp3()
    {
        return resIdtemp3;
    }

    /**
     * Method setResIdtemp3 sets the resIdtemp3 of this GameGlo object.
     *
     *  Field resIdtemp3
     *
     * @param resIdtemp3 the resIdtemp3 of this GameGlo object.
     *
     */
    public void setResIdtemp3(int resIdtemp3)
    {
        this.resIdtemp3 = resIdtemp3;
    }

    /**
     * Method getResIdtemp4 returns the resIdtemp4 of this GameGlo object.
     *
     *  Field resIdtemp4
     *
     * @return the resIdtemp4 (type int) of this GameGlo object.
     */
    public int getResIdtemp4()
    {
        return resIdtemp4;
    }

    /**
     * Method setResIdtemp4 sets the resIdtemp4 of this GameGlo object.
     *
     *  Field resIdtemp4
     *
     * @param resIdtemp4 the resIdtemp4 of this GameGlo object.
     *
     */
    public void setResIdtemp4(int resIdtemp4)
    {
        this.resIdtemp4 = resIdtemp4;
    }

    /**
     * Method getResIdtemp5 returns the resIdtemp5 of this GameGlo object.
     *
     *  Field resIdtemp5
     *
     * @return the resIdtemp5 (type int) of this GameGlo object.
     */
    public int getResIdtemp5()
    {
        return resIdtemp5;
    }

    /**
     * Method setResIdtemp5 sets the resIdtemp5 of this GameGlo object.
     *
     *  Field resIdtemp5
     *
     * @param resIdtemp5 the resIdtemp5 of this GameGlo object.
     *
     */
    public void setResIdtemp5(int resIdtemp5)
    {
        this.resIdtemp5 = resIdtemp5;
    }

    /**
     * Method getResIdtemp1 returns the resIdtemp1 of this GameGlo object.
     *
     *  Field resIdtemp5
     *
     * @return the resIdtemp1 (type int) of this GameGlo object.
     */
    public int getResIdtemp1()
    {
        return resIdtemp1;
    }

    /**
     * Method setResIdtemp1 sets the resIdtemp1 of this GameGlo object.
     *
     *  Field resIdtemp5
     *
     * @param resIdtemp1 the resIdtemp1 of this GameGlo object.
     *
     */
    public void setResIdtemp1(int resIdtemp1)
    {
        this.resIdtemp1 = resIdtemp1;
    }

    /**
     * Method getIntPos1 returns the intPos1 of this GameGlo object.
     *
     *  Field intPos1
     *
     * @return the intPos1 (type int) of this GameGlo object.
     */
    public int getIntPos1()
    {
        return intPos1;
    }

    /**
     * Method setIntPos1 sets the intPos1 of this GameGlo object.
     *
     *  Field intPos1
     *
     * @param intPos1 the intPos1 of this GameGlo object.
     *
     */
    public void setIntPos1(int intPos1)
    {
        this.intPos1 = intPos1;
    }

    /**
     * Method getIntPos2 returns the intPos2 of this GameGlo object.
     *
     *  Field intPos2
     *
     * @return the intPos2 (type int) of this GameGlo object.
     */
    public int getIntPos2()
    {
        return intPos2;
    }

    /**
     * Method setIntPos2 sets the intPos2 of this GameGlo object.
     *
     *  Field intPos2
     *
     * @param intPos2 the intPos2 of this GameGlo object.
     *
     */
    public void setIntPos2(int intPos2)
    {
        this.intPos2 = intPos2;
    }

    /**
     * Method getIntPos3 returns the intPos3 of this GameGlo object.
     *
     *  Field intPos3
     *
     * @return the intPos3 (type int) of this GameGlo object.
     */
    public int getIntPos3()
    {
        return intPos3;
    }

    /**
     * Method setIntPos3 sets the intPos3 of this GameGlo object.
     *
     *  Field intPos3
     *
     * @param intPos3 the intPos3 of this GameGlo object.
     *
     */
    public void setIntPos3(int intPos3)
    {
        this.intPos3 = intPos3;
    }

    /**
     * Method getIntPos5 returns the intPos5 of this GameGlo object.
     *
     *  Field intPos5
     *
     * @return the intPos5 (type int) of this GameGlo object.
     */
    public int getIntPos5()
    {
        return intPos5;
    }

    /**
     * Method setIntPos5 sets the intPos5 of this GameGlo object.
     *
     *  Field intPos5
     *
     * @param intPos5 the intPos5 of this GameGlo object.
     *
     */
    public void setIntPos5(int intPos5)
    {
        this.intPos5 = intPos5;
    }

    /**
     * Method getIntPos4 returns the intPos4 of this GameGlo object.
     *
     *  Field intPos4
     *
     * @return the intPos4 (type int) of this GameGlo object.
     */
    public int getIntPos4()
    {
        return intPos4;
    }

    /**
     * Method setIntPos4 sets the intPos4 of this GameGlo object.
     *
     *  Field intPos4
     *
     * @param intPos4 the intPos4 of this GameGlo object.
     *
     */
    public void setIntPos4(int intPos4)
    {
        this.intPos4 = intPos4;
    }

    /**
     * Method getStrResult returns the strResult of this GameGlo object.
     *
     *  Field strResult
     *
     * @return the strResult (type String) of this GameGlo object.
     */
    public String getStrResult()
    {
        return strResult;
    }

    /**
     * Method setStrResult sets the strResult of this GameGlo object.
     *
     *  Field strResult
     *
     * @param strResult the strResult of this GameGlo object.
     *
     */
    public void setStrResult(String strResult)
    {
        this.strResult = strResult;
    }

    /**
     * Method getPayType returns the payType of this GameGlo object.
     *
     *  Field payType
     *
     * @return the payType (type String) of this GameGlo object.
     */
    public String getPayType()
    {
        return payType;
    }

    /**
     * Method setPayType sets the payType of this GameGlo object.
     *
     *  Field payType
     *
     * @param payType the payType of this GameGlo object.
     *
     */
    public void setPayType(String payType)
    {
        this.payType = payType;
    }


    public Boolean getForResult()
    {
        return forResult;
    }

    public void setForResult(Boolean forResult)
    {
        this.forResult = forResult;
    }

    public Boolean getDissmissAble()
    {
        return dissmissAble;
    }

    public void setDissmissAble(Boolean dissmissAble)
    {
        this.dissmissAble = dissmissAble;
    }

    public int getLefttimes()
    {
        return lefttimes;
    }

    public void setLefttimes(int lefttimes)
    {
        this.lefttimes = lefttimes;
    }
}
