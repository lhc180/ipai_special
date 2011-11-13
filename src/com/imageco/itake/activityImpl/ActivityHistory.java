package com.imageco.itake.activityImpl;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.imageco.R;
import com.imageco.itake.adapterCus.SettingAdapter;
import com.imageco.itake.gloable.Constant;
import com.imageco.itake.gloable.HistoryListStatus;
import com.imageco.util.DataOperation;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;


/**
 * Class ActivityHistory ...
 *
 * @author Administrator
 *         Created on 11-10-22
 */
public class ActivityHistory extends Activity {

//    private Button deleteAll;//删除全部

    /**
     * Field clearList
     */
    private ArrayList<Integer> clearList = null;

    /**
     * Field statusMap
     */
    private HashMap<String, Boolean> statusMap;//选项状态
    private Button deleteAll;
    private Boolean isAll = false;


    /**
     * Called when the activityImpl is first created.
     */
    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.histroy);

        deleteAll = (Button) ActivityHistory.this.findViewById(R.id.deleteAll);

        deleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!isAll) {//初始
                    refreshUI(Boolean.TRUE);
                    isAll = Boolean.TRUE;
                } else {
                    refreshUI(Boolean.FALSE);
                    isAll = Boolean.FALSE;
                }

            }
        });

        initList();//初始化列表
    }

    private void refreshUI(Boolean checked) {
        ListView clearListView = (ListView) findViewById(R.id.clear);

        statusMap = new HashMap<String, Boolean>();
        clearList = new ArrayList<Integer>();


        ArrayList<JSONObject> mArrayList = DataOperation.readFile(Constant.FILENAME);
        int arraySize = mArrayList.size();
        String[] textParams1 = new String[arraySize];
        String[] textParams2 = new String[arraySize];
        for (int i = 0; i < arraySize; i++) {
            try {
                textParams1[i] = mArrayList.get(i).getString(Constant.FILECONTANT);
                textParams2[i] = mArrayList.get(i).getString(Constant.FILEDATE);
                statusMap.put(i + "", checked);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        HistoryListStatus.getInstance().setPositionStatus(statusMap);

        ArrayList<Integer> mPosition = new ArrayList<Integer>();
        for (int i = 0; i < textParams1.length; i++) {
            mPosition.add(i);
        }
        setAdapter(clearListView, textParams1, textParams2, mPosition);

    }

    /**
     * Method initList ...
     */
    private void initList() {
        ListView clearListView = (ListView) findViewById(R.id.clear);
        Button clearButton = (Button) findViewById(R.id.clearButton);

        statusMap = new HashMap<String, Boolean>();
        clearList = new ArrayList<Integer>();


        ArrayList<JSONObject> mArrayList = DataOperation.readFile(Constant.FILENAME);
        int arraySize = mArrayList.size();
        String[] textParams1 = new String[arraySize];
        String[] textParams2 = new String[arraySize];
        for (int i = 0; i < arraySize; i++) {
            try {
                textParams1[i] = mArrayList.get(i).getString(Constant.FILECONTANT);
                textParams2[i] = mArrayList.get(i).getString(Constant.FILEDATE);
                statusMap.put(i + "", Boolean.FALSE);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        HistoryListStatus.getInstance().setPositionStatus(statusMap);

//        clearButton.setVisibility(View.VISIBLE);


        ArrayList<Integer> mPosition = new ArrayList<Integer>();
        for (int i = 0; i < textParams1.length; i++) {
            mPosition.add(i);
        }
        setAdapter(clearListView, textParams1, textParams2, mPosition);


        clearButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Method onClick ...
             *
             * @param v of type View
             */
            public void onClick(View v) {
                clearList = getClearList();

                if (clearList.size() > 0) {
//                if (true) {
                    new AlertDialog.Builder(ActivityHistory.this)
                            .setTitle("清空数据提示")
                            .setMessage("您确定要清空上述勾选的数据吗？")
                            .setPositiveButton(R.string.alert_dialog_ok,
                                    new DialogInterface.OnClickListener() {
                                        /**
                                         * Method onClick ...
                                         *
                                         * @param dialog of type DialogInterface
                                         * @param which  of type int
                                         */
                                        public void onClick(DialogInterface dialog, int which) {
//删除操作

                                            for (int j = 0; j < clearList.size(); j++) {
                                                System.out.println("删除列表的长度=================" + clearList.size());
                                                DataOperation.deleteFileItemInPos(Constant.FILENAME, clearList.get(j));
                                            }
                                            refreshUI(Boolean.FALSE);
//                                            initList();
                                        }

                                    })
                            .setNegativeButton("取消", //设置“取消”按钮
                                    new DialogInterface.OnClickListener() {
                                        /**
                                         * Method onClick ...
                                         *
                                         * @param dialog      of type DialogInterface
                                         * @param whichButton of type int
                                         */
                                        public void onClick(DialogInterface dialog, int whichButton) {
                                            return;
                                        }
                                    })
                            .show();
                } else {
                    Toast.makeText(ActivityHistory.this, "请选择数据后再点清除按钮", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    /**
     * Method setAdapter ...
     *
     * @param listView     of type ListView
     * @param text1        of type String[]
     * @param text2        of type String[]
     * @param positionList of type ArrayList<Integer>
     */
    public void setAdapter(ListView listView, String[] text1, String[] text2, ArrayList<Integer> positionList) {
        ArrayList<HashMap<String, Object>> data = getData(text1, text2);
        SettingAdapter adapter = new SettingAdapter(data, this, positionList,ActivityResult.class);
        setListener(listView);
        listView.setAdapter(adapter);


    }

    /**
     * Method setListener sets the listener of this ActivityHistory object.
     *
     * @param listView the listener of this ActivityHistory object.
     */
    public void setListener(ListView listView) {

        listView.setOnItemClickListener(listener);


    }

    /**
     * Method getData ...
     *
     * @param text1 of type String[]
     * @param text2 of type String[]
     * @return view显示数据array
     */
    private ArrayList<HashMap<String, Object>> getData(String[] text1, String[] text2) {
        ArrayList<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < text1.length; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            String param1 = "";
            String param2 = "";
            if (text1[i] != null && text2[i] != null) {
                param1 = text1[i];
                param2 = text2[i].substring(0, 10);
            }

            map.put("itemselectedImage", R.drawable.listview_selected);// 添加图像资源的ID
            map.put("itemName", param1);// 按序号做ItemText
            map.put("itemName2", param2);
            data.add(map);
        }

        return data;
    }

    /**
     * Field listener
     */
    AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
        /**
         * Method onItemClick ...
         *
         * @param parent of type AdapterView<?>
         * @param view of type View
         * @param position of type int
         * @param id of type long
         */

        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {

            LinearLayout layout = (LinearLayout) view;

            ImageView imageView = (ImageView) layout.getChildAt(3);

            Boolean selected = HistoryListStatus.getInstance().getPositionStatus().get(position + "");//获取当前position的状态

            if (selected) {
                imageView.setVisibility(View.INVISIBLE);
                statusMap.put(position + "", false);

            } else {
                imageView.setVisibility(View.VISIBLE);
                statusMap.put(position + "", true);


            }
            HistoryListStatus.getInstance().setPositionStatus(statusMap);


        }

    };

    /**
     * Method getClearList returns the clearList of this ActivityHistory object.
     * <p/>
     * Field clearList
     *
     * @return the clearList (type ArrayList<Integer>) of this ActivityHistory object.
     */
    private ArrayList<Integer> getClearList() {
        ArrayList<Integer> arr = new ArrayList<Integer>();
        for (Iterator iterator = statusMap.entrySet().iterator(); iterator.hasNext(); ) {
            Properties.Entry next = (Map.Entry) iterator.next();//{1,false}
            String key = (String) next.getKey();
            Boolean value = (Boolean) next.getValue();
            if (value == true) {
                arr.add(Integer.parseInt(key));
            }
        }
//        arr.reverse();
//        System.out.println("排列前array============="+arr.size());
        Collections.sort(arr);
        return reverse(arr);
    }


    /**
     * Method reverse ..倒序排列
     *
     * @param arraylist of type ArrayList<Integer>
     * @return ArrayList<Integer>
     */
    private ArrayList<Integer> reverse(ArrayList<Integer> arraylist) {
        Stack<Integer> mStack = new Stack<Integer>();
        ArrayList<Integer> mArray = new ArrayList<Integer>();
        for (int i = 0; i < arraylist.size(); i++) {
            mStack.push(arraylist.get(i));
        }
//        System.out.println("排列中Stack============="+mStack.size());
        int length = mStack.size();
        for (int j = 0; j < length; j++) {
            mArray.add(mStack.pop());
        }
//        System.out.println("排列后array============="+mArray.size());
        return mArray;

    }

}