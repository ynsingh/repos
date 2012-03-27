package aell
import org.apache.commons.lang.builder.HashCodeBuilder

class AvlRoleUserRel implements Serializable {

	AvlUserMaster user
	AvlRoleMaster role
	boolean equals(other) {
		if (!(other instanceof AvlRoleUserRel)) {
			return false
		}

		other.user?.id == user?.id &&
			other.role?.id == role?.id
	}

	int hashCode() {
		def builder = new HashCodeBuilder()
		if (user) builder.append(user.id)
		if (role) builder.append(role.id)
		builder.toHashCode()
	}

	static AvlRoleUserRel get(long userId, long roleId) {
		find 'from AvlRoleUserRel where user.id=:userId and role.id=:roleId',
			[userId: userId, roleId: roleId]
	}

	static AvlRoleUserRel create(AvlUserMaster user, AvlRoleMaster role, boolean flush = false) {
		new AvlRoleUserRel(user: user, role: role).save(flush: flush, insert: true)
	}

	static boolean remove(AvlUserMaster user, AvlRoleMaster role, boolean flush = false) {
		AvlRoleUserRel instance = AvlRoleUserRel.findByAvlUserMasterAndAvlRoleMaster(user, role)
		instance ? instance.delete(flush: flush) : false
	}

	static void removeAll(AvlUserMaster user) {
		executeUpdate 'DELETE FROM AvlRoleUserRel WHERE user=:user', [user: user]
	}

	static void removeAll(AvlRoleMaster role) {
		executeUpdate 'DELETE FROM AvlRoleUserRel WHERE role=:role', [role: role]
	}

	static mapping = {
		id composite: ['role', 'user']
		version false
	}
}

