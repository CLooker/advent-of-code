const solution = ([wire1, wire2]) => {
  const xToYs1 = (() => {
    let x = 0;
    let y = 0;
    let ret = {};

    wire1.forEach(value => {
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
        if (x in ret) ret[x].add(y);
        else ret[x] = new Set().add(y);
        update();
      }
    });

    return ret;
  })();

  const xToYsIntersection = (() => {
    let x = 0;
    let y = 0;
    let ret = {};

    wire2.forEach(value => {
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
        if (x in xToYs1 && xToYs1[x].has(y)) {
          if (x in ret) ret[x].add(y);
          else ret[x] = new Set().add(y);
        }
        update();
      }
    });

    return ret;
  })();

  const xyToStepsArr = [wire1, wire2].reduce((accum, wire, i) => {
    let x = 0;
    let y = 0;
    let steps = 0;

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
          update = _ => {
            x--;
            steps++;
          };
          break;
        }
        case 'R': {
          limit = x + magnitude;
          shouldContinue = _ => x < limit;
          update = _ => {
            x++;
            steps++;
          };
          break;
        }
        case 'D': {
          limit = y - magnitude;
          shouldContinue = _ => y > limit;
          update = _ => {
            y--;
            steps++;
          };
          break;
        }
        case 'U': {
          limit = y + magnitude;
          shouldContinue = _ => y < limit;
          update = _ => {
            y++;
            steps++;
          };
          break;
        }
        default:
          break;
      }

      update();
      while (shouldContinue()) {
        if (x in xToYsIntersection && xToYsIntersection[x].has(y)) {
          const xStr = x.toString();
          const yStr = y.toString();
          const xy = xStr + yStr;
          if (xy in accum) {
            const xyToStepsArrItem = accum[xy][i];
            if (steps < xyToStepsArrItem) accum[xy][i] = steps;
          } else {
            accum[xy] = Array.from({ length: 2 }, (_, j) =>
              j === i ? steps : Infinity
            );
          }
        }
        update();
      }
    });

    return accum;
  }, {});

  const smallestCombinedSteps = Object.values(xyToStepsArr).reduce(
    (accum, stepsArr) => {
      const combinedSteps = stepsArr.reduce((acc, steps) => acc + steps, 0);
      return Math.min(accum, combinedSteps);
    },
    Infinity
  );

  return smallestCombinedSteps;
};

module.exports = solution;
