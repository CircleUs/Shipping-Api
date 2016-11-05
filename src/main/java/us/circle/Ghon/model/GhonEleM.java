package us.circle.Ghon.model;

import java.util.ArrayList;
import java.util.List;

import us.circle.Ghon.utils.GhonUtil;


public class GhonEleM implements GhonEleB {
	
	private List<GhonEleN> objects;

	public int getType() {
		return GhonEle.BASEMAP;
	}

	public List<GhonEleN> getObjects() {
		if(objects == null){
			objects = new ArrayList<GhonEleN>();
		}
		return objects;
	}

	public void setObjects(List<GhonEleN> objects) {
		this.objects = objects;
	}

	@Override
	public byte[] getBytes() {
		return GhonUtil.mapToBytes(objects, (byte)getType());
	}

	@Override
	public String toJSON(boolean isGhon) {
		if(objects == null || objects.isEmpty()){
			return "{}";
		}
		StringBuilder sb = new StringBuilder("{");
		for(int i=0;i<objects.size();i++){
			GhonEleN ghonEleN = objects.get(i);
			sb.append(ghonEleN.toJSON(isGhon));
			if(i<objects.size() -1){
				sb.append(",");
			}
		}
		sb.append("}");
		return sb.toString();
	}

}
