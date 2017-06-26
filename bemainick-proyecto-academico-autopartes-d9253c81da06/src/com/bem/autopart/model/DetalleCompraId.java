package com.bem.autopart.model;

import java.io.Serializable;

public class DetalleCompraId implements Serializable {

	private static final long serialVersionUID = 1L;
	private long autoparteId;
	private long compraId;

	public DetalleCompraId() {

	}
	
	
	public long getAutoparteId() {
		return autoparteId;
	}


	public void setAutoparteId(long autoparteId) {
		this.autoparteId = autoparteId;
	}


	public long getCompraId() {
		return compraId;
	}


	public void setCompraId(long compraId) {
		this.compraId = compraId;
	}


	public int hashCode() {
	    return (int)(autoparteId + compraId);
	  }

	  public boolean equals(Object object) {
	    if (object instanceof DetalleCompraId) {
	    	DetalleCompraId otherId = (DetalleCompraId) object;
	      return (otherId.autoparteId == this.autoparteId) && (otherId.compraId == this.compraId);
	    }
	    return false;
	  }

	

}
