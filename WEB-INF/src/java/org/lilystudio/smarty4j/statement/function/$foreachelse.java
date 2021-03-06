package org.lilystudio.smarty4j.statement.function;

import java.util.Map;

import org.lilystudio.smarty4j.statement.Function;
import org.lilystudio.smarty4j.statement.ParameterCharacter;
import org.lilystudio.smarty4j.statement.ParentType;
import org.objectweb.asm.MethodVisitor;

/**
 * 参见foreach函数。
 * 
 * @see org.lilystudio.smarty4j.statement.function.$foreach
 * 
 * @version 1.0.0, 2010/10/01
 * @author 欧阳先伟
 * @since Smarty 1.0
 */
@ParentType(name = "foreach")
public class $foreachelse extends Function {

  public ParameterCharacter[] getDefinitions() {
    return null;
  }

  public void parse(MethodVisitor mw, int local,
      Map<String, Integer> variableNames) {
  }
}
