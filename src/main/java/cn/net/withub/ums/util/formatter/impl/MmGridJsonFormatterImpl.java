package cn.net.withub.ums.util.formatter.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cn.net.withub.ums.util.formatter.DataPack;
import cn.net.withub.ums.util.formatter.Formatter;
import cn.net.withub.ums.util.formatter.impl.FlexiGridJsonFormatterImpl.FlexiGrid;
import cn.net.withub.ums.util.formatter.impl.FlexiGridJsonFormatterImpl.FlexiGridRow;

public class MmGridJsonFormatterImpl implements Formatter {
	private static Logger logger = LogManager.getLogger(MmGridJsonFormatterImpl.class.getName());

	@Override
	public Object format(DataPack data) {
		 MmGrid mmgGrid = new MmGrid();
		 mmgGrid.setTotalCount(data.getTotal());

		 //创建装载数据的集合
		 List<Map<String,Object>> items = new ArrayList<Map<String,Object>>();
	        if (data.getData() instanceof Collection) {
	            Collection c = (Collection) data.getData();
	            
	            //循环数据行
	            for (Object o : c) {
	                Object id = null;
	                try {
	                	//创建行的装载容器
	                	Map<String,Object> item = new HashMap<String, Object>();
	                	
	                	//循环行的列装到容器中
	                	for(Field field : o.getClass().getFields()){
	                		
	                		try {
	                			String name = field.getName();//获取列名
		                		Object value = field.get(o);//获取列数据
		                		
		                		item.put(name, value);
		                		
		                		//把行数据装入大集合中
		                		items.add(item);
							} catch (Exception e) {
								logger.error(e.getMessage());
							}
	                		
	                	}
	                	
	                } catch (Exception e) {
	                }

	            }
	        } else if (data.getData() instanceof Map) {
	            Map m = (Map) data.getData();

	            for (Object key : m.keySet()) {
	            	//创建行的装载容器
                	Map<String,Object> item = new HashMap<String, Object>();
	            	try {
	                	item.put(key.toString(), m.get(key));
					} catch (Exception e) {
						logger.error(e.getMessage());
					}
	            	//把行数据装入大集合中
            		items.add(item);
	            }
	        }
	        
	        
	        //装载到对象中
	        mmgGrid.setItems(items);

	        return mmgGrid;
	}
	
	
	public class MmGrid {
		private int totalCount;
		private List<Map<String,Object>> items;
		
		public int getTotalCount() {
			return totalCount;
		}
		public void setTotalCount(int totalCount) {
			this.totalCount = totalCount;
		}
		public List<Map<String, Object>> getItems() {
			return items;
		}
		public void setItems(List<Map<String, Object>> items) {
			this.items = items;
		}
		
	}

}


