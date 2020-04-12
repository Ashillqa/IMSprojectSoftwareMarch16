package tables;

public class Orders {
	
	private int oid;
	private int cid;
	private int pid;
	private int quantity;
	
	
	public Orders(int oid,int pid, int quantity) {
		this.oid=oid;	
		this.pid=pid;
		this.quantity=quantity;
	}
	
	public Orders(int oid,int cid, int pid, int quantity) {
		this.oid=oid;
		this.cid=cid;
		this.pid= pid;
		this.quantity=quantity;
	}

	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cid;
		result = prime * result + oid;
		result = prime * result + pid;
		result = prime * result + quantity;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Orders other = (Orders) obj;
		if (cid != other.cid)
			return false;
		if (oid != other.oid)
			return false;
		if (pid != other.pid)
			return false;
		if (quantity != other.quantity)
			return false;
		return true;
	}
}
