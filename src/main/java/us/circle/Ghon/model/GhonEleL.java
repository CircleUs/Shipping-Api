package us.circle.Ghon.model;

import java.util.ArrayList;
import java.util.List;

import us.circle.Ghon.utils.GhonUtil;


public class GhonEleL implements GhonEleB {

	private List<GhonEleB> objects;
	
	public int getType() {
		return GhonEle.BASELIST;
	}
	
	public List<GhonEleB> getObjects() {
		if(objects == null){
			objects = new ArrayList<GhonEleB>();
		}
		return objects;
	}

	public void setObjects(List<GhonEleB> objects) {
		this.objects = objects;
	}

	@Override
	public byte[] getBytes() {
		return GhonUtil.listToBytes(objects, (byte)getType());
	}

	@Override
	public String toJSON(boolean isGhon) {
		if(objects == null || objects.isEmpty()){
			return "[]";
		}
		StringBuilder sb = new StringBuilder("[");
		for(int i=0;i<objects.size();i++){
			GhonEleB ghonEleB = objects.get(i);
			sb.append(ghonEleB.toJSON(isGhon));
			if(i<objects.size() -1){
				sb.append(",");
			}
		}
		sb.append("]");
		return sb.toString();
	}

}
