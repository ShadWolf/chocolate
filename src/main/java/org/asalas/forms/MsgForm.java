package org.asalas.forms;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MsgForm {
	private String nextpage;
	private String msg = "n";
	private String err = "n";
	@Override
	public String toString() {
		return "MsgForm [nextpage=" + nextpage + ", msg=" + msg + ", err=" + err + "]";
	}
		
}
