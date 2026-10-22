/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.contentai.workbench;

import static cn.zhuatech.contentai.workbench.WorkbenchInput.*;

import java.math.*;
import java.util.*;
import org.springframework.stereotype.Service;

/** 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。 */
@Service
public class AiWorkbenchService {
  /** 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。 */
  public Map<String, Object> execute(Map<String, Object> r) {
    validate(r);
    keys(r, "text", "maxChars", "requiredTerms", "blockedTerms");
    String value = text(r, "text", 32000);
    int max = integer(r, "maxChars", 1, 32000);
    var required = strings(r, "requiredTerms", 0, 100);
    var blocked = strings(r, "blockedTerms", 0, 100);
    if (new HashSet<>(required).size() != required.size()
        || new HashSet<>(blocked).size() != blocked.size())
      throw new IllegalArgumentException("规则词条重复");
    List<Map<String, Object>> issues = new ArrayList<>();
    int length = value.codePointCount(0, value.length());
    if (length > max) issues.add(Map.of("type", "TOO_LONG", "actual", length, "limit", max));
    for (String term : required)
      if (!value.contains(term)) issues.add(Map.of("type", "MISSING_REQUIRED", "term", term));
    for (String term : blocked) {
      int from = 0, at;
      while ((at = value.indexOf(term, from)) >= 0) {
        if (issues.size() >= 1000) throw new IllegalArgumentException("命中问题超过1000项，请分段处理");
        issues.add(Map.of("type", "BLOCKED_TERM", "term", term, "offsetUtf16", at));
        from = at + term.length();
      }
    }
    return Map.of(
        "passed",
        issues.isEmpty(),
        "characterCount",
        length,
        "issues",
        issues,
        "matching",
        "CASE_SENSITIVE_LITERAL");
  }
}
