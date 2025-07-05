-- Regional Sales Analysis Query
WITH region_stats AS (
  SELECT 
    r.name AS region_name,
    COALESCE(SUM(s.amount), 0) AS total_sales,
    COUNT(DISTINCT e.id) AS employee_count,
    CASE 
      WHEN COUNT(DISTINCT e.id) > 0 THEN COALESCE(SUM(s.amount), 0) / COUNT(DISTINCT e.id)
      ELSE 0 
    END AS avg_sales_per_employee
  FROM regions r
  LEFT JOIN states st ON r.id = st.regionId
  LEFT JOIN employees e ON st.id = e.stateId
  LEFT JOIN sales s ON e.id = s.employeeId
  GROUP BY r.id, r.name
),
max_avg AS (
  SELECT MAX(avg_sales_per_employee) AS highest_avg_sales
  FROM region_stats
)
SELECT 
  rs.region_name,
  rs.avg_sales_per_employee,
  (ma.highest_avg_sales - rs.avg_sales_per_employee) AS difference_from_highest
FROM region_stats rs
CROSS JOIN max_avg ma
ORDER BY rs.avg_sales_per_employee DESC;


SELECT userId, AVG(duration) AS average_duration
FROM sessions
GROUP BY userId
HAVING COUNT(*) > 1;