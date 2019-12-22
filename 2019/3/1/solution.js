const solution = ([wire1, wire2]) => {
  const [xToYs1, xToYs2] = [wire1, wire2].map(wire => {
    let x = 0;
    let y = 0;
    let xToYs = {};

    wire.forEach(value => {
      const direction = value.charAt(0);
      const magnitude = +value.slice(1);

      let limit;
      let shouldContinue;
      let update;
      switch (direction) {
        case 'L': {
          limit = x - magnitude;
          shouldContinue = _ => x > limit;
          update = _ => x--;
          break;
        }
        case 'R': {
          limit = x + magnitude;
          shouldContinue = _ => x < limit;
          update = _ => x++;
          break;
        }
        case 'D': {
          limit = y - magnitude;
          shouldContinue = _ => y > limit;
          update = _ => y--;
          break;
        }
        case 'U': {
          limit = y + magnitude;
          shouldContinue = _ => y < limit;
          update = _ => y++;
          break;
        }
        default:
          break;
      }

      update();
      while (shouldContinue()) {
        if (x in xToYs) xToYs[x].add(y);
        else xToYs[x] = new Set().add(y);
        update();
      }
    });

    return xToYs;
  });

  const xToYsIntersection = Object.entries(xToYs1).reduce((accum, [x, ys1]) => {
    if (!(x in xToYs2)) return accum;

    const ysIntersection = new Set();
    const ys2 = xToYs2[x];

    ys1.forEach(y => ys2.has(y) && ysIntersection.add(y));

    accum[x] = ysIntersection;
    return accum;
  }, {});

  const smallestDistance = Object.entries(xToYsIntersection).reduce(
    (accum, [x, ys]) => {
      const xAbsValue = Math.abs(x);

      ys.forEach(y => {
        const yAbsValue = Math.abs(y);
        const distance = xAbsValue + yAbsValue;
        if (distance < accum) accum = distance;
      });

      return accum;
    },
    Infinity
  );

  return smallestDistance;
};

module.exports = solution;
